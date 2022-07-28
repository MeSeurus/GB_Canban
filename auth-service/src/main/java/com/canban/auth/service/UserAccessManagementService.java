package com.canban.auth.service;

import com.canban.auth.activemqevents.ChangeStatusEvent;
import com.canban.auth.config.JmsConfig;
import com.canban.auth.entity.User;
import com.canban.auth.entity.security.UserStatus;
import com.canban.auth.repository.ActivationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


@Service
@RequiredArgsConstructor
public class UserAccessManagementService {
    private final UserService userService;
    private final JmsTemplate jmsTemplate;
    private final ActivationRepository activationRepository;

    private List<User> users = new CopyOnWriteArrayList<>();

    @Transactional
    public boolean checkActivateKey(String username, String secretCode) {
        if (secretCode.equals(activationRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username))).getSecretCode())) {
            userService.updateStatus(username,UserStatus.ACTIVE);
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
        if (user == null) {
            user = new User(username);
        } else {
            isFinded = true;
        }
        user.setMistakeNum(user.getMistakeNum() + 1);
        if (!isFinded) {
            users.add(user);
        }
        if (user.getMistakeNum() >= 3) {
            stopGuessingPassword(user.getUsername());
            users.remove(user);
            return true;
        }
        return false;
    }

    public void stopGuessingPassword(String username){
        userService.updateStatus(username,UserStatus.SUSPICIOUS);
        jmsTemplate.setDeliveryDelay(60000L);
        jmsTemplate.convertAndSend(JmsConfig.STATUS_CHANGE, new ChangeStatusEvent(username));
    }


}
