package com.canban.web.analytics.mappers;

import com.canban.api.analytics.EventAnalyticsDto;
import com.canban.web.analytics.entities.EventAnalytics;
import org.springframework.stereotype.Component;

@Component
public class EventAnalyticsMapper {

    public EventAnalytics dtoToEntity(EventAnalyticsDto eventAnalyticsDto) {
        return new EventAnalytics(
                eventAnalyticsDto.getEventId(),
                eventAnalyticsDto.getEventTitle(),
                eventAnalyticsDto.getEventUsername(),
                eventAnalyticsDto.getEventBeginDate(),
                eventAnalyticsDto.getEventEndDate()
        );
    }

    public EventAnalyticsDto entityToDto(EventAnalytics eventAnalytics) {
        return new EventAnalyticsDto(
                eventAnalytics.getEventId(),
                eventAnalytics.getEventTitle(),
                eventAnalytics.getEventUsername(),
                eventAnalytics.getEventBeginDate(),
                eventAnalytics.getEventEndDate()
        );
    }

}
