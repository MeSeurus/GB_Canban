package com.canban.web.analytics.mappers;

import com.canban.api.analytics.EventsAnalyticsDto;
import com.canban.web.analytics.entities.EventsAnalytics;
import org.springframework.stereotype.Component;

import java.time.Period;
import java.time.temporal.TemporalUnit;

@Component
public class EventsAnalyticsMapper {

    public EventsAnalytics dtoToEntity(EventsAnalyticsDto eventsAnalyticsDto) {
        return new EventsAnalytics(
                eventsAnalyticsDto.getEventId(),
                eventsAnalyticsDto.getEventTitle(),
                eventsAnalyticsDto.getEventUsername(),
                eventsAnalyticsDto.getEventBeginDate(),
                eventsAnalyticsDto.getEventEndDate()
                );
    }

    public EventsAnalyticsDto entityToDto(EventsAnalytics eventsAnalytics) {
        return new EventsAnalyticsDto(
                eventsAnalytics.getEventId(),
                eventsAnalytics.getEventTitle(),
                eventsAnalytics.getEventUsername(),
                eventsAnalytics.getEventBeginDate(),
                eventsAnalytics.getEventEndDate()
        );
    }

}
