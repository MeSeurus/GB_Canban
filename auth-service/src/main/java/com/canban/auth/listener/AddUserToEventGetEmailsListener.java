package com.canban.auth.listener;

import com.canban.api.activemqevents.AddUserToEventGetEmailsEvent;
import com.canban.auth.config.JmsConfig;
import com.canban.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;


@RequiredArgsConstructor
@Component
public class AddUserToEventGetEmailsListener {

    private final UserService userService;

    @JmsListener(destination = JmsConfig.STATUS_CHANGE)
    public void listen(@Payload AddUserToEventGetEmailsEvent addUserToEventGetEmailsEvent) {
        Map<String,String> usersMap = userService.getEmailsByUsernames(addUserToEventGetEmailsEvent.getUsers());
        userService.sendAddUserToEventSendToMailServiceEvent(addUserToEventGetEmailsEvent.getUsername(),usersMap);
    }
}