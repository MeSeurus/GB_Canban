package com.canban.api.analytics;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Обертка листа моделей события для сервиса аналитики")
public class EventsAnalyticsDtoWithList {
    @Schema(description = "Список EventsAnalyticsDto", required = true)
    private List<EventAnalyticsDto> eventAnalyticsDtoList;

    public EventsAnalyticsDtoWithList(List<EventAnalyticsDto> eventAnalyticsDtoList) {
        this.eventAnalyticsDtoList = eventAnalyticsDtoList;
    }

    public EventsAnalyticsDtoWithList() {
    }

    public List<EventAnalyticsDto> getEventsAnalyticsDtoList() {
        return eventAnalyticsDtoList;
    }

    public void setEventsAnalyticsDtoList(List<EventAnalyticsDto> eventAnalyticsDtoList) {
        this.eventAnalyticsDtoList = eventAnalyticsDtoList;
    }
}
