package com.canban.web.analytics.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Модель общей статистики задач сервиса аналитики")
public class AllStatisticsTaskAnalyticsRs {

    @Schema(description = "Название самой длинной задачи", required = true)
    private String longestTaskTitle;

    @Schema(description = "Название самого короткой задачи", required = true)
    private String shortestTaskTitle;

    @Schema(description = "Количество задач", required = true)
    private Integer countOfTasks;

}
