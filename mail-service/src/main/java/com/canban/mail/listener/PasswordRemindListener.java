package com.canban.mail.listener;

import com.canban.api.activemqevents.PasswordRemindEvent;
import com.canban.mail.config.JmsConfig;
import com.canban.mail.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PasswordRemindListener {
    private final MailSenderService mailSenderService;

    @JmsListener(destination = JmsConfig.PASSWORD_REMIND)
    public void listen(@Payload PasswordRemindEvent passwordRemindEventEvent) {
        mailSenderService.sendMail(passwordRemindEventEvent.getEmail(), "Код для восстановление пароля аккаунта в Canban", " Уважаемый " + passwordRemindEventEvent.getUsername() + "!\n" +
                " Высылаем Вам код, который необходимо ввести для сброса пароля вашего аккаунта.\n Код: "
                + passwordRemindEventEvent.getPasscode() + "\n Если Вы не отправляли это письмо, просто проигнорируйте его.");

    }
}
