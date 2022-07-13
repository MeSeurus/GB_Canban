package com.canban.web.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель деталей события")
public class EventDetails {
    @Schema(description = "Название события", required = true)
    private String title;
    @Schema(description = "Описание события", required = true)
    private String content;

    @Schema(description = "Пользователь события")
    private String username;

    public EventDetails(String title, String content, String username) {
        this.title = title;
        this.content = content;
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
