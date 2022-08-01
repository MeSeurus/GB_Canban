package com.canban.web.core.dto;

import com.canban.web.core.enums.Priority;
import com.canban.web.core.enums.State;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


import java.time.LocalDateTime;


@Schema(description = "Модель деталей задачи")
@Data


public class TaskDetailsRq {
    @Schema(description = "Название задачи", required = true)
    private String title;

    @Schema(description = "Описание задачи", required = true)
    private String content;

    @Schema(description = "Дата начала задачи")
    LocalDateTime beginDate;

    @Schema(description = "Срок выполнения задачи")
    private LocalDateTime endDate;

    @Schema(description = "Дата фактического выполнения задачи")
    private LocalDateTime actualEndDate;

    @Schema(description = "Статус задачи")
    private State state;

    @Schema(description = "Приоритет задачи")
    private Priority priority;

    @Schema(description = "Имя kanban доски")
    private String kanbanName;


}

