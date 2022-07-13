package com.canban.web.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Модель деталей задачи")
@Setter
@Getter
public class TaskDetails {
    @Schema(description = "Название задачи", required = true)
    private String title;
    @Schema(description = "Описание задачи", required = true)
    private String content;

    @Schema(description = "Исполнитель задачи")
    private String username;

    public TaskDetails(String title, String content, String userName) {
        this.title = title;
        this.content = content;
        this.username = userName;
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

    public String getUserName() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
