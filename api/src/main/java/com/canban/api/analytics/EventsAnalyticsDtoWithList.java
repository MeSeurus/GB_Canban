package com.canban.api.analytics;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "Обертка листа моделей события для сервиса аналитики")
public class EventsAnalyticsDtoWithList {
    @Schema(description = "Список EventsAnalyticsDto", required = true)
    private List<EventsAnalyticsDto> eventsAnalyticsDtoList;

    public EventsAnalyticsDtoWithList(List<EventsAnalyticsDto> eventsAnalyticsDtoList) {
        this.eventsAnalyticsDtoList = eventsAnalyticsDtoList;
    }

    public EventsAnalyticsDtoWithList() {
    }

    public List<EventsAnalyticsDto> getEventsAnalyticsDtoList() {
        return eventsAnalyticsDtoList;
    }

    public void setEventsAnalyticsDtoList(List<EventsAnalyticsDto> eventsAnalyticsDtoList) {
        this.eventsAnalyticsDtoList = eventsAnalyticsDtoList;
    }
}
