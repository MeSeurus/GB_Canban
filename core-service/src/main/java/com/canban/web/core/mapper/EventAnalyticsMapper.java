package com.canban.web.core.mapper;

import com.canban.api.analytics.EventAnalyticsDto;
import com.canban.web.core.entities.Event;
import com.canban.web.core.entities.EventForAnalytics;
import org.springframework.stereotype.Component;


@Component
public class EventAnalyticsMapper {

    public EventForAnalytics eventToEventForAnalytics(Event event) {
        return new EventForAnalytics(
                event.getId(),
                event.getTitle(),
                event.getUsername(),
                event.getBeginDate(),
                event.getEndDate()
        );
    }

    public EventAnalyticsDto entityToDto(EventForAnalytics eventForAnalytics) {
        return new EventAnalyticsDto(
                eventForAnalytics.getId(),
                eventForAnalytics.getEventTitle(),
                eventForAnalytics.getEventUsername(),
                eventForAnalytics.getEventBeginDate(),
                eventForAnalytics.getEventEndDate()
        );
    }

}
