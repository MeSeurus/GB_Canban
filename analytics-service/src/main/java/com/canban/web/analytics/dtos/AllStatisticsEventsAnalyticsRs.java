package com.canban.web.analytics.dtos;

import com.canban.api.analytics.EventsAnalyticsDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Модель общей статистики ивентов сервиса аналитики")
public class AllStatisticsEventsAnalyticsRs {

    @Schema(description = "Самый длинный ивент", required = true)
    private EventsAnalyticsDto longestEvent;

    @Schema(description = "Самый короткий ивент", required = true)
    private EventsAnalyticsDto shortestEvent;

    @Schema(description = "Количество ивентов", required = true)
    private Integer countOfEvents;

}
