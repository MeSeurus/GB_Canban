package com.canban.web.core.mapper;

import com.canban.api.analytics.EventsAnalyticsDto;
import com.canban.web.core.entities.Event;
import org.springframework.stereotype.Component;


@Component
public class EventAnalyticsMapper {

    public EventsAnalyticsDto entityToDto(Event event) {
        return new EventsAnalyticsDto(
                event.getId(),
                event.getTitle(),
                event.getUsername(),
                event.getBeginDate(),
                event.getEndDate(),
                event.getUsers()
        );
    }

}
