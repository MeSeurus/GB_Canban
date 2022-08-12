package com.canban.api.activemqevents;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Событие, которое происходит после извлечения email пользователей из базы" +
        " и передается брокеру сообщений для отправки в mail-service, чтобы разослать уведомления по почте")
public class AddUserToEventSendToMailServiceEvent {
    String username;
    Map<String, String> users;
    String eventTitle;
}
