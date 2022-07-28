package com.canban.api.core;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "Модель задачи")
@Getter
@Setter
@ToString
public class TaskDto {
    @Schema(description = "ID задачи", required = true, example = "1")
    private Long id;
    @Schema(description = "Название задачи", required = true, example = "Создать программу Hello World")
    private String title;

    @Schema(description = "Описание задачи", required = true)
    private String content;


    @Schema(description = "Исполнитель задачи", required = true, example = "user1")
    private String username;

    @Schema(description = "Дата начала задачи", required = true)
    private LocalDateTime endDate;

    @Schema(description = "Срок выполнения задачи", required = true)
    private LocalDateTime beginDate;

    @Schema(description = "Статус задачи", required = true)
    private String state;

    @Schema(description = "Приоритет задачи", required = true)
    private String priority;

    @Schema(description = "Имя канбан-доски", required = true)
    private String kanbanName;

//Конструктор без исполнителя
    public TaskDto(Long id,
                   String title,
                   String content,
                   LocalDateTime eventDate,
                   LocalDateTime dueDate,
                   String state,
                   String priority,
                   String kanbanName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.endDate = eventDate;
        this.beginDate = dueDate;
        this.state = state;
        this.priority = priority;
        this.kanbanName = kanbanName;
    }

    public TaskDto(Long id, String title, String content, String username, LocalDateTime eventDate, LocalDateTime dueDate, String state, String priority, String kanbanName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.username = username;
        this.endDate = eventDate;
        this.beginDate = dueDate;
        this.state = state;
        this.priority = priority;
        this.kanbanName = kanbanName;
    }
}
