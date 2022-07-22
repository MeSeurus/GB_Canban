package com.canban.web.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Модель деталей события")
public class EventDetailsRq {
    @Schema(description = "Название события", required = true)
    private String title;
    @Schema(description = "Описание события", required = true)
    private String content;

    @Schema(description = "Пользователь события")
    private String username;

    @Schema(description = "Дата назначения события")
    private LocalDateTime eventDate;

    public EventDetailsRq(String title, String content, String username, LocalDateTime eventDate) {
        this.title = title;
        this.content = content;
        this.username = username;
        this.eventDate = eventDate;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }
}
