package com.canban.api.activemqevents;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Событие, которое происходит в момент автоматической смены статуса пользователя по каким-то причинам (например попытка подбора пароля)" +
        " и передается брокеру сообщений для отправки в auth-service, чтобы сервис восстановил пользователю статус спустя необходимое время.")
public class ChangeStatusEvent {
    @Schema(description = "Никнейм пользователя")
    private String username;

}
