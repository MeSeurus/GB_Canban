package com.canban.web.analytics.dtos;

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

    @Schema(description = "Название самого длинного события", required = true)
    private String longestEventTitle;

    @Schema(description = "Название самого короткого события", required = true)
    private String shortestEventTitle;

    @Schema(description = "Количество событий", required = true)
    private Integer countOfEvents;

}
