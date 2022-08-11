package com.canban.web.analytics.dtos;

import com.canban.api.analytics.AnalyticsTitleDto;
import com.canban.api.analytics.EventAnalyticsDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Модель общей статистики событий сервиса аналитики")
public class AllStatisticsEventAnalyticsRs {


    @Schema(description = "Самый длинный ивент", required = true)
    private AnalyticsTitleDto longestEvent;

    @Schema(description = "Самый короткий ивент", required = true)
    private AnalyticsTitleDto shortestEvent;

    @Schema(description = "Количество событий", required = true)
    private Integer countOfEvents;

}
