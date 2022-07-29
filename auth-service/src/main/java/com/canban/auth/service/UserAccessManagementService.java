package com.canban.auth.service;

import com.canban.auth.activemqevents.ChangeStatusEvent;
import com.canban.auth.config.JmsConfig;
import com.canban.auth.entity.User;
import com.canban.auth.entity.security.CodeType;
import com.canban.auth.entity.security.UserAwaitActivation;
import com.canban.auth.entity.security.UserStatus;
import com.canban.auth.repository.ActivationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;


@Service
@RequiredArgsConstructor
public class UserAccessManagementService {

    /**
     * Это нужно для устранения Circular Dependency Exception
     */

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private final JmsTemplate jmsTemplate;
    private final ActivationRepository activationRepository;
    private final MailSenderService mailSenderService;

    private List<User> users = new CopyOnWriteArrayList<>();

    @Transactional
    public boolean checkActivateKey(String username, String secretCode) {
        UserAwaitActivation userAwaitActivation = activationRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        if (userAwaitActivation.getCodeType() == CodeType.ACTIVATION_CODE && userAwaitActivation.getSecretCode().equals(secretCode)){
            userService.updateStatus(username,UserStatus.ACTIVE);
            activationRepository.deleteById(username);
            return true;
        } return false;
    }

    @Transactional
    public void setNewPassword(String username, String password, String code) {
        UserAwaitActivation userAwaitActivation = activationRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        if (userAwaitActivation.getCodeType() == CodeType.PASSWORD_REMIND_CODE && userAwaitActivation.getSecretCode().equals(code)) {
            userService.updatePassword(username, password);
        }
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

    public void sendRecoverPasswordLink(String username){
           String userEmail = userService.getEmailByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
           String passcode = getRandomNumberString();
           activationRepository.save(new UserAwaitActivation(username,passcode,CodeType.PASSWORD_REMIND_CODE));
           mailSenderService.sendMail(userEmail,"Canban password recovery link","http://localhost:5555/auth/setnewpassword/?username=" + username + "&passcode=" + passcode);
    }

    private String getRandomNumberString (){
        Random random = new Random();
        int number = random.nextInt(999999);
        return String.format("%06d", number);
    }


    public void createUser(UserAwaitActivation userAwaitActivation) {
        activationRepository.save(userAwaitActivation);
    }
}
