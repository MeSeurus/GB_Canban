package com.canban.web.analytics.dtos;

import com.canban.api.analytics.TaskAnalyticsDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Модель общей статистики задач сервиса аналитики")
public class AllStatisticsTaskAnalyticsRs {

    @Schema(description = "Самая длинная задача", required = true)
    private TaskAnalyticsDto longestTask;

    @Schema(description = "Самая короткая задача", required = true)
    private TaskAnalyticsDto shortestTask;

    @Schema(description = "Количество задач", required = true)
    private Integer countOfTasks;

}
