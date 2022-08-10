package com.canban.web.chat.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Уведомления чата")
public class ChatNotification {

    @Schema(description = "ID сообщения", required = true, example = "1")
    private String id;

    @Schema(description = "ID отправителя", required = true, example = "1")
    private String senderId;

    @Schema(description = "Имя получателя", required = true, example = "1")
    private String senderName;
}
