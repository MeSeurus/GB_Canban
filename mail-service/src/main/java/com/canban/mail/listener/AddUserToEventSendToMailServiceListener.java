package com.canban.mail.listener;


import com.canban.api.activemqevents.AddUserToEventSendToMailServiceEvent;
import com.canban.api.activemqevents.PasswordRemindEvent;
import com.canban.mail.config.JmsConfig;
import com.canban.mail.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@RequiredArgsConstructor
@Component
public class AddUserToEventSendToMailServiceListener {

    private final MailSenderService mailSenderService;

    @JmsListener(destination = JmsConfig.ADD_TO_EVENT_SEND_TO_MAIL_SERVICE)
    public void listen(@Payload AddUserToEventSendToMailServiceEvent addUserToEventSendToMailServiceEvent) {

        for (Map.Entry<String, String> entry : addUserToEventSendToMailServiceEvent.getUsers().entrySet()) {
            //todo передавать ID события. к которому добавили, изменить текст письма в связи с этим.
            mailSenderService.sendMail(entry.getKey(),"Вас добавили в событие Canban!" , " Уважаемый " + entry.getValue() + "!\n " + addUserToEventSendToMailServiceEvent.getUsername() + " добавил Вас к событию " + addUserToEventSendToMailServiceEvent.getEventTitle());
        }
    }
}
