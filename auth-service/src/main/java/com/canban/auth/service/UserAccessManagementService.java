package com.canban.auth.service;

import com.canban.auth.entity.User;
import com.canban.auth.entity.security.UserStatus;
import com.canban.auth.repository.ActivationRepository;
import com.canban.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


@Service
@RequiredArgsConstructor
public class UserAccessManagementService {
    private final UserRepository userRepository;
    private final ActivationRepository activationRepository;

    private List<User> users = new CopyOnWriteArrayList<>();

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

    /**
     * Проверка на попытку подбора пароля к аккаунту
     */
    public boolean passwordGuessingProtection(String username) {

        boolean isFinded = false;
        User user = users.stream().filter(us -> us.getUsername().equals(username)).findFirst().orElse(null);
        if (user == null)  {
            user = new User(username);
            isFinded = true;
        }
        user.setMistakeNum(user.getMistakeNum() + 1);
        if (!isFinded) {
            users.add(user);
        }
        if (user.getMistakeNum() >= 3) {
            user.setMistakeNum(0);
            return true;
        }
        return false;
    }


}
