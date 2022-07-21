package com.canban.auth.service;

import com.canban.auth.entity.Role;
import com.canban.auth.entity.User;
import com.canban.auth.entity.security.UserAwaitActivation;
import com.canban.auth.entity.security.UserStatus;
import com.canban.auth.repository.ActivationRepository;
import com.canban.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
    private final ActivationRepository activationRepository;
    private final RoleService roleService;
    private final MailSenderService mailSenderService;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
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
        activationRepository.save(userAwaitActivation);
        mailSenderService.sendMail(user.getEmail(),"Canban activation link","http://localhost:5555/auth/activation/?username=" + userAwaitActivation.getUsername() + "&code=" + userAwaitActivation.getSecretCode());
    }

    @Transactional
    public boolean checkActivateKey(String username, String secretCode) {
        if (secretCode.equals(activationRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username))).getSecretCode())) {
            User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
            user.setUserStatus(UserStatus.ACTIVE);
            userRepository.save(user);
            activationRepository.deleteById(username);
            return true;
        } return false;
    }

    private String getRandomNumberString (){
        Random random = new Random();
        int number = random.nextInt(999999);
        return String.format("%06d", number);
    }
}