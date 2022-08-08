package com.canban.api.analytics;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "Обертка листа моделей задач для сервиса аналитики")
public class TasksAnalyticsDtoWithList {

    @Schema(description = "Список TasksAnalyticsDto", required = true)
    private List<TasksAnalyticsDto> tasksAnalyticsDtoList;

    public TasksAnalyticsDtoWithList(List<TasksAnalyticsDto> tasksAnalyticsDtoList) {
        this.tasksAnalyticsDtoList = tasksAnalyticsDtoList;
    }

    public TasksAnalyticsDtoWithList() {
    }

    public List<TasksAnalyticsDto> getTasksAnalyticsDtoList() {
        return tasksAnalyticsDtoList;
    }

    public void setTasksAnalyticsDtoList(List<TasksAnalyticsDto> tasksAnalyticsDtoList) {
        this.tasksAnalyticsDtoList = tasksAnalyticsDtoList;
    }
}
