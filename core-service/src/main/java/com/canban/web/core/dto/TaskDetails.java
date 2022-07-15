package com.canban.web.core.dto;

import com.canban.api.core.Priority;
import com.canban.api.core.State;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;


@Schema(description = "Модель деталей задачи")
@Data

public class TaskDetails {
    @Schema(description = "Название задачи", required = true)
    private String title;
    @Schema(description = "Описание задачи", required = true)
    private String content;

    @Schema(description = "Исполнитель задачи")
    private String username;

    @Schema(description = "Статус задачи")
    private State state;

    @Schema(description = "Приоритет задачи")
    private Priority priority;

    @Schema(description = "Имя canban доски")
    private String kanban_name;

    @Schema(description = "Начало задачи")
    private LocalDateTime event_date;

    @Schema(description = "Срок задачи")
    private LocalDateTime due_date;

}
