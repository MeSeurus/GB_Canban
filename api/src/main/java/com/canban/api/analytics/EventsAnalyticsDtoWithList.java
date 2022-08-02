package com.canban.api.analytics;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "Обертка листа моделей события для сервиса аналитики")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EventsAnalyticsDtoWithList {
    private List<EventsAnalyticsDto> eventsAnalyticsDtoList;
}
