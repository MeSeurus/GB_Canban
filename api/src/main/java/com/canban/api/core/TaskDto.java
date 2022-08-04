package com.canban.api.core;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;
@Schema(description = "Модель задачи")
@Getter
@Setter
@ToString
@AllArgsConstructor
public class TaskDto {

    @Schema(description = "ID задачи", required = true, example = "1")
    private Long id;

    @Schema(description = "Название задачи", required = true, example = "Создать программу Hello World")
    private String title;

    @Schema(description = "Описание задачи", required = false    )
    private String content;

    @Schema(description = "Создатель задачи", required = true, example = "user1")
    private String userCreator;
    @Schema(description = "Исполнитель задачи", required = false, example = "user1")
    private String userExecutor;

    @Schema(description = "Дата начала задачи", required = true)
    private LocalDateTime beginDate;

    @Schema(description = "Дата выполнения задачи", required = true)
    private LocalDateTime endDate;

    @Schema(description = "Дата фактического выполнения задачи", required = false)
    private LocalDateTime actualEndDate;

    @Schema(description = "Статус задачи", required = true)
    private String state;

    @Schema(description = "Приоритет задачи", required = true)
    private String priority;

    @Schema(description = "ID канбан-доски", required = true)
    private Long kanbanBoardId;

    public TaskDto(Long id, String title, String content, String userExecutor, LocalDateTime beginDate, LocalDateTime endDate, LocalDateTime actualEndDate, String state, String priority, Long kanbanBoardId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userExecutor = userExecutor;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.actualEndDate = actualEndDate;
        this.state = state;
        this.priority = priority;
        this.kanbanBoardId = kanbanBoardId;
    }

    public TaskDto(Long id, String title, String content, LocalDateTime beginDate, LocalDateTime endDate, LocalDateTime actualEndDate, String state, String priority, Long kanbanBoardId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.actualEndDate = actualEndDate;
        this.state = state;
        this.priority = priority;
        this.kanbanBoardId = kanbanBoardId;
    }
}