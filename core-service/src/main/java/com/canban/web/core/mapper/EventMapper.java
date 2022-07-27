package com.canban.web.core.mapper;
import com.canban.api.core.EventDto;
import com.canban.web.core.entities.Event;
import org.springframework.stereotype.Component;
@Component
public class EventMapper {

    public EventDto entityToDto(Event event) {
        return new EventDto(event.getId(), event.getTitle(), event.getContent(), event.getUsername(), event.getBeginDate());
    }

}