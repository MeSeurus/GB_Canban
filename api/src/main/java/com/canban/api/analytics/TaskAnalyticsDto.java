package com.canban.api.analytics;

import com.canban.api.core.Priority;
import com.canban.api.core.State;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Модель задачи для сервиса аналитики")
public class TaskAnalyticsDto {

    @Schema(description = "ID задачи", required = true, example = "1")
    private Long taskId;

    @Schema(description = "Название задачи", required = true, example = "Create program")
    private String taskTitle;

    @Schema(description = "Имя пользователя создателя задачи", required = true, example = "user1")
    private String taskUserCreator;

    @Schema(description = "Имя пользователя исполнителя задачи", required = true, example = "user2")
    private String taskUserExecutor;

    @Schema(description = "Дата начала задачи", required = true, example = "2022-06-24 01:00:00")
    private LocalDateTime taskBeginDate;

    @Schema(description = "Срок исполнения задачи", required = true, example = "2022-06-24 18:00:00")
    private LocalDateTime taskEndDate;

    @Schema(description = "Фактическая дата окончания задачи", required = true, example = "2022-06-24 18:00:00")
    private LocalDateTime taskActualEndDate;

    @Schema(description = "Статус задачи", required = true, example = "CREATED")
    private State taskState;

    @Schema(description = "Приоритет задачи", required = true, example = "HIGH")
    private Priority taskPriority;

    @Schema(description = "Id канбан-доски задачи", required = true, example = "1")
    private Long kanbanBoardId;

    public TaskAnalyticsDto(String taskTitle) {
        this.taskTitle = taskTitle;
    }
}
