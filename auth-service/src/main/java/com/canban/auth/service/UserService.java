package com.canban.auth.service;

import com.canban.api.activemqevents.ActivationEvent;
import com.canban.auth.config.JmsConfig;
import com.canban.auth.entity.Role;
import com.canban.auth.entity.User;
import com.canban.auth.entity.security.CodeType;
import com.canban.auth.entity.security.UserAwaitActivation;
import com.canban.auth.entity.security.UserStatus;
import com.canban.auth.exceptions.InvalidAuthorizationException;
import com.canban.auth.exceptions.InvalidRegistrationException;
import com.canban.auth.exceptions.UserNotActiveException;
import com.canban.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private UserAccessManagementService userAccessManagementService;
    private final JmsTemplate jmsTemplate;

    @Autowired
    public void setUserAccessManagementService(@Lazy UserAccessManagementService userAccessManagementService) {
        this.userAccessManagementService = userAccessManagementService;
    }

    public Optional<User> findByUsername(String username) {return userRepository.findByUsername(username);}

    public Optional<User> findByEmail(String email) {return userRepository.findByEmail(email);}

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь '%s' не найден", username)));
        if (user.getUserStatus() == UserStatus.NOT_ACTIVE)
            throw new UserNotActiveException(String.format("Пользователь '%s' не активирован", username));
        if (user.getUserStatus() == UserStatus.SUSPICIOUS)
            throw new InvalidAuthorizationException(String.format("Пользователь '%s' превысил количество попыток ввода пароля", username));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Transactional
    public void createUser(User user) {
        if (findByUsername(user.getUsername()).isPresent()) {throw new InvalidRegistrationException("Пользователь с таким именем уже существует");}
        if (findByEmail(user.getEmail()).isPresent()) {throw new InvalidRegistrationException("Пользователь с таким email уже существует");}
        user.setUserStatus(UserStatus.NOT_ACTIVE);
        userRepository.save(user);
        UserAwaitActivation userAwaitActivation = new UserAwaitActivation();
        userAwaitActivation.setUsername(user.getUsername());
        userAwaitActivation.setSecretCode(getRandomNumberString());
        userAwaitActivation.setCodeType(CodeType.ACTIVATION_CODE);
        userAccessManagementService.createUser(userAwaitActivation);
        jmsTemplate.convertAndSend(JmsConfig.ACTIVATION, new ActivationEvent(userAwaitActivation.getUsername(), userAwaitActivation.getSecretCode(), user.getEmail()));
    }

    private String getRandomNumberString() {
        Random random = new Random();
        int number = random.nextInt(999999);
        return String.format("%06d", number);
    }

    @Transactional
    public void updateStatus(String username, UserStatus userStatus) {
        userRepository.updateStatus(username, userStatus);
    }

    public Optional<String> getEmailByUsername(String username) {
        return userRepository.getEmailByUsername(username);
    }

    public Optional<UserStatus> getUserStatusByUsername(String username) {return userRepository.getUserStatusByUsername(username);}

    @Transactional
    public void updatePassword(String username, String password) {
        userRepository.updatePassword(username, password);
    }

    public boolean userExistInDb(String username) {
        return userRepository.findExistingUser(username) == 1;
    }
}