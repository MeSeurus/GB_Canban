package com.canban.api.analytics;

import com.canban.api.core.Priority;
import com.canban.api.core.State;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Schema(description = "Модель события для сервиса аналитики")
public class TasksAnalyticsDto {

    @Schema(description = "ID задачи", required = true, example = "1")
    private Long taskId;

    @Schema(description = "Название задачи", required = true, example = "Create program")
    private String taskTitle;

    @Schema(description = "Имя пользователя задачи", required = true, example = "user1")
    private String taskUsername;

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

    @Schema(description = "Имя канбан-доски задачи", required = true, example = "HIGH")
    private String taskKanbanName;

    public TasksAnalyticsDto(Long taskId, String taskTitle, String taskUsername, LocalDateTime taskBeginDate, LocalDateTime taskEndDate, LocalDateTime taskActualEndDate, State taskState, Priority taskPriority, String taskKanbanName) {
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskUsername = taskUsername;
        this.taskBeginDate = taskBeginDate;
        this.taskEndDate = taskEndDate;
        this.taskActualEndDate = taskActualEndDate;
        this.taskState = taskState;
        this.taskPriority = taskPriority;
        this.taskKanbanName = taskKanbanName;
    }

    public TasksAnalyticsDto() {
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskUsername() {
        return taskUsername;
    }

    public void setTaskUsername(String taskUsername) {
        this.taskUsername = taskUsername;
    }

    public LocalDateTime getTaskBeginDate() {
        return taskBeginDate;
    }

    public void setTaskBeginDate(LocalDateTime taskBeginDate) {
        this.taskBeginDate = taskBeginDate;
    }

    public LocalDateTime getTaskEndDate() {
        return taskEndDate;
    }

    public void setTaskEndDate(LocalDateTime taskEndDate) {
        this.taskEndDate = taskEndDate;
    }

    public LocalDateTime getTaskActualEndDate() {
        return taskActualEndDate;
    }

    public void setTaskActualEndDate(LocalDateTime taskActualEndDate) {
        this.taskActualEndDate = taskActualEndDate;
    }

    public State getTaskState() {
        return taskState;
    }

    public void setTaskState(State taskState) {
        this.taskState = taskState;
    }

    public Priority getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(Priority taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getTaskKanbanName() {
        return taskKanbanName;
    }

    public void setTaskKanbanName(String taskKanbanName) {
        this.taskKanbanName = taskKanbanName;
    }
}
