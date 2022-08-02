package com.canban.api.analytics;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "Обертка листа моделей задач для сервиса аналитики")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TasksAnalyticsDtoWithList {

    private List<TasksAnalyticsDto> tasksAnalyticsDtoList;

}
