package com.canban.web.chat.models;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Перечисления статусов сообщений")
public enum MessageStatus {
    RECEIVED, DELIVERED
}
