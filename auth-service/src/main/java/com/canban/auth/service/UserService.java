package com.canban.auth.service;

import com.canban.auth.entity.Role;
import com.canban.auth.entity.User;
import com.canban.auth.entity.security.CodeType;
import com.canban.auth.entity.security.UserAwaitActivation;
import com.canban.auth.entity.security.UserStatus;
import com.canban.auth.exceptions.InvalidAuthorizationException;
import com.canban.auth.exceptions.UserNotActiveException;
import com.canban.auth.repository.ActivationRepository;
import com.canban.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final MailSenderService mailSenderService;
    private final UserAccessManagementService userAccessManagementService;

    /**
     * Это нужно для устранения Circular Dependency Exception
     */

    @PostConstruct
    private void init(){
        userAccessManagementService.setUserService(this);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь '%s' не найден", username)));
        if (user.getUserStatus() == UserStatus.NOT_ACTIVE) throw new UserNotActiveException(String.format("Пользователь '%s' не активирован", username));
        if (user.getUserStatus() == UserStatus.SUSPICIOUS) throw new InvalidAuthorizationException(String.format("Пользователь '%s' превысил количество попыток ввода пароля", username));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Transactional
    public void createUser(User user) {
        user.setUserStatus(UserStatus.NOT_ACTIVE);
        userRepository.save(user);
        UserAwaitActivation userAwaitActivation = new UserAwaitActivation();
        userAwaitActivation.setUsername(user.getUsername());
        userAwaitActivation.setSecretCode(getRandomNumberString());
        userAwaitActivation.setCodeType(CodeType.ACTIVATION_CODE);
        userAccessManagementService.createUser(userAwaitActivation);
        mailSenderService.sendMail(user.getEmail(),"Canban activation link","http://localhost:5555/auth/activation/?username=" + userAwaitActivation.getUsername() + "&code=" + userAwaitActivation.getSecretCode());
    }

    private String getRandomNumberString (){
        Random random = new Random();
        int number = random.nextInt(999999);
        return String.format("%06d", number);
    }

    @Transactional
    public void updateStatus (String username, UserStatus userStatus){
        userRepository.updateStatus(username, userStatus);
    }

    public Optional<String> getEmailByUsername (String username){
        return userRepository.getEmailByUsername(username);

    }

    @Transactional
    public void updatePassword (String username, String password){
        userRepository.updatePassword(username, password);
    }
}