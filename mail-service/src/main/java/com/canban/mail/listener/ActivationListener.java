package com.canban.mail.listener;

import com.canban.api.activemqevents.ActivationEvent;
import com.canban.mail.config.JmsConfig;
import com.canban.mail.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.jms.annotation.JmsListener;


@RequiredArgsConstructor
@Component
public class ActivationListener {

    private final MailSenderService mailSenderService;

    @JmsListener(destination = JmsConfig.ACTIVATION)
    public void listen(@Payload ActivationEvent activationEvent) {
        mailSenderService.sendMail(activationEvent.getEmail(), "Ссылка на активацию аккаунта в Canban", "http://localhost:5555/auth/api/v1/user/access/management/activation/?username=" + activationEvent.getUsername() + "&code=" + activationEvent.getCode());

    }
}