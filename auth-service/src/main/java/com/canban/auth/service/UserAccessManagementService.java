package com.canban.auth.service;

import com.canban.api.activemqevents.ActivationEvent;
import com.canban.api.activemqevents.ChangeStatusEvent;
import com.canban.api.activemqevents.PasswordRemindEvent;
import com.canban.auth.config.JmsConfig;
import com.canban.auth.entity.User;
import com.canban.auth.entity.security.CodeType;
import com.canban.auth.entity.security.UserAwaitActivation;
import com.canban.auth.entity.security.UserStatus;
import com.canban.auth.exceptions.InvalidRegistrationException;
import com.canban.auth.exceptions.WrongUserStatusException;
import com.canban.auth.repository.ActivationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.canban.auth.cnst.RegexConst.VALIDATE_PASSWORD;


@Service
@RequiredArgsConstructor
public class UserAccessManagementService {
    private final UserService userService;
    private final JmsTemplate jmsTemplate;
    private final ActivationRepository activationRepository;

    private List<User> users = new CopyOnWriteArrayList<>();

    public Optional<UserAwaitActivation> findByUsername(String username) {
        return activationRepository.findByUsername(username);
    }

    @Transactional
    public boolean checkActivateKey(String username, String secretCode) {
        UserAwaitActivation userAwaitActivation = activationRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь '%s' не найден", username)));
        if (userAwaitActivation.getCodeType() == CodeType.ACTIVATION_CODE && userAwaitActivation.getSecretCode().equals(secretCode)) {
            userService.updateStatus(username, UserStatus.ACTIVE);
            activationRepository.deleteById(username);
            return true;
        }
        return false;
    }

    @Transactional
    public void setNewPassword(String username, String password, String code) {
        if (!password.matches(VALIDATE_PASSWORD)) {throw new InvalidRegistrationException("Пароль не должен быть меньше 8 символов");}
        if (!userAndCodeExistInDb(username, code)) {throw new InvalidRegistrationException("Некорректная ссылка");}
        UserAwaitActivation userAwaitActivation = activationRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь '%s' не найден", username)));
        if (userAwaitActivation.getCodeType() == CodeType.PASSWORD_REMIND_CODE && userAwaitActivation.getSecretCode().equals(code)) {
            userService.updatePassword(username, password);
            activationRepository.deleteById(username);
        }
    }

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

    public void stopGuessingPassword(String username) {
        userService.updateStatus(username, UserStatus.SUSPICIOUS);
        jmsTemplate.setDeliveryDelay(60000L);
        jmsTemplate.convertAndSend(JmsConfig.STATUS_CHANGE, new ChangeStatusEvent(username));
    }

    @Transactional
    public void sendRecoverPasswordLink(String username) {
        String userEmail = userService.getEmailByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь '%s' не найден", username)));
        String passcode = getRandomNumberString();
        activationRepository.save(new UserAwaitActivation(username, passcode, CodeType.PASSWORD_REMIND_CODE));
        jmsTemplate.convertAndSend(JmsConfig.PASSWORD_REMIND, new PasswordRemindEvent(username, passcode, userEmail));
    }

    private String getRandomNumberString() {
        Random random = new Random();
        int number = random.nextInt(999999);
        return String.format("%06d", number);
    }

    @Transactional
    public void createUser(UserAwaitActivation userAwaitActivation) {
        activationRepository.save(userAwaitActivation);
    }


    public boolean userAndCodeExistInDb(String username, String passcode) {
        return activationRepository.findExistingUserAndCode(username, passcode, CodeType.PASSWORD_REMIND_CODE);
    }

    public void sendActivationLinkInQueue(String username, String code, String email){
        jmsTemplate.convertAndSend(JmsConfig.ACTIVATION, new ActivationEvent(username,code, email));
    }

    @Transactional
    public void sendNewActivationLink(String username) {
        if (!userService.getUserStatusByUsername(username).get().equals(UserStatus.NOT_ACTIVE)) {throw new WrongUserStatusException("Вы уже активированы");}
        String newActivationCode = getRandomNumberString();
        String email = userService.getEmailByUsername(username).get();
        if (activationRepository.findExistingUserAndCodeType(username, CodeType.ACTIVATION_CODE)) {
            activationRepository.updateCodeByUsernameAndCodeType(username,newActivationCode,CodeType.ACTIVATION_CODE);
        } else {
            UserAwaitActivation user = new UserAwaitActivation(username,newActivationCode,CodeType.ACTIVATION_CODE);
            activationRepository.save(user);
        }
        sendActivationLinkInQueue(username, newActivationCode, email);
    }
}
