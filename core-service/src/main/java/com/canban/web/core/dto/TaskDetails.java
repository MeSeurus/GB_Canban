package com.canban.web.core.dto;

import com.canban.web.core.enums.Priority;
import com.canban.web.core.enums.State;
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
    private String canbanName;

    @Schema(description = "Начало задачи")
    private LocalDateTime eventDate;

    @Schema(description = "Срок задачи")
    private LocalDateTime dueDate;

}