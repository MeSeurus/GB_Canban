package com.canban.web.core.dto;

import com.canban.api.core.Priority;
import com.canban.api.core.State;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @Schema(description = "Имя исполнителя задачи", required = true)
    private String userExecutor;

    @Schema(description = "Дата начала задачи")
    @JsonProperty("start_date")
    private LocalDateTime beginDate;

    @Schema(description = "Срок выполнения задачи")
    @JsonProperty("end_date")
    private LocalDateTime endDate;

    @Schema(description = "Дата фактического выполнения задачи")
    private LocalDateTime actualEndDate;

    @Schema(description = "Статус задачи")
    private State state;

    @Schema(description = "Приоритет задачи")
    private Priority priority;

    @Schema(description = "ID kanban доски")
    private Long kanbanBoardId;


}

