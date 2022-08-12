package com.canban.api.activemqevents;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Событие, которое происходит в момент добавления пользователя или пользователей к событию" +
        " и передается брокеру сообщений для отправки в auth-service, чтобы сервис отдал в mail-service пользователей и их email")
public class AddUserToEventSendToMailServiceEvent {
    String username;
    Map<String, String> users;
}
