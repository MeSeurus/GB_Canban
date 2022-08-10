package com.canban.api.analytics;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Обертка листа моделей задач для сервиса аналитики")
public class TasksAnalyticsDtoWithList {

    @Schema(description = "Список TasksAnalyticsDto", required = true)
    private List<TaskAnalyticsDto> taskAnalyticsDtoList;

    public TasksAnalyticsDtoWithList(List<TaskAnalyticsDto> taskAnalyticsDtoList) {
        this.taskAnalyticsDtoList = taskAnalyticsDtoList;
    }

    public TasksAnalyticsDtoWithList() {
    }

    public List<TaskAnalyticsDto> getTasksAnalyticsDtoList() {
        return taskAnalyticsDtoList;
    }

    public void setTasksAnalyticsDtoList(List<TaskAnalyticsDto> taskAnalyticsDtoList) {
        this.taskAnalyticsDtoList = taskAnalyticsDtoList;
    }
}
