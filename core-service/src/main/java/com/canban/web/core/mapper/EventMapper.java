package com.canban.web.core.mapper;

import com.canban.api.core.EventDto;
import com.canban.web.core.entities.Event;
import org.springframework.stereotype.Component;


@Component
public class EventMapper {

    public Event dtoToEntity(EventDto eventDto) {
        throw new UnsupportedOperationException();
    }

    public EventDto entityToDto(Event event) {
        return new EventDto(event.getId(), event.getTitle(), event.getContent(), event.getDay().getWeekday(), event.getUser().getNickname(), event.getEventStart(), event.getEventEnd());
    }

}
