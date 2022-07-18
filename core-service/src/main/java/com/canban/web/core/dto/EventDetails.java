package com.canban.web.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Модель деталей события")
@Data
public class EventDetails {
    @Schema(description = "Название события", required = true)
    private String title;
    @Schema(description = "Описание события", required = true)
    private String content;

    @Schema(description = "Пользователь события")
    private String username;

    public EventDetails(String title, String content, String userName) {
        this.title = title;
        this.content = content;
        this.username = userName;
    }
}
