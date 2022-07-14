package com.canban.api.core;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Schema(description = "Модель задачи")
@Getter
@Setter
@ToString
public class TaskDto {
    /*@Schema(description = "ID задачи", required = true, example = "1")
    private Long id;
    @Schema(description = "Название задачи", required = true, example = "Создать программу Hello World")
    private String title;

    @Schema(description = "Описание задачи", required = true)
    private String content;


    @Schema(description = "Исполнитель задачи", required = true, example = "user1")
    private String username;

    /**
     * Вот тут вопрос, событие на один день или на несколько?
     * Если на один, то смысла не имеет, можно дату создания запоминать просто

    @Schema(description = "Дата начала задачи", required = true)
    private LocalDateTime eventDate;

    @Schema
    private State


    public TaskDto(Long id, String title, String content, String username, LocalDateTime eventDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.username = username;
        this.eventDate = eventDate;
    }

    public EventDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

*/
}
