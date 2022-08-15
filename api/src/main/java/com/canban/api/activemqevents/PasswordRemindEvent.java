package com.canban.api.activemqevents;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Событие, которое происходит в момент запроса ссылки на сброс пароля пользователя и передается брокеру сообщений " +
        "для отправки в mail-service, чтобы сервис отправил этому пользователю ссылку по email")
public class PasswordRemindEvent {
    @Schema(description = "Никнейм пользователя")
    private String username;
    @Schema(description = "Код для сброса пароля")
    private String passcode;
    @Schema(description = "Email пользователя")
    private String email;
}
