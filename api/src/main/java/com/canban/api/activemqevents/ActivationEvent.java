package com.canban.api.activemqevents;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Событие, которое происходит в момент момент запроса ссылки на активацию аккаунта пользователя " +
        "и передается брокеру сообщений для отправки в mail-service, чтобы сервис отправил этому пользователю ссылку по email")
public class ActivationEvent {
    @Schema(description = "Никнейм пользователя")
    private String username;
    @Schema(description = "Код для активации аккаунта")
    private String code;
    @Schema(description = "Email пользователя")
    private String email;
}
