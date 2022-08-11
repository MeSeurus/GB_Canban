package com.canban.web.analytics.dtos;

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

    @Schema(description = "Самое длинное событие", required = true)
    private EventAnalyticsDto longestEvent;

    @Schema(description = "Самое короткое событие", required = true)
    private EventAnalyticsDto shortestEvent;

    @Schema(description = "Количество событий", required = true)
    private Integer countOfEvents;

}
