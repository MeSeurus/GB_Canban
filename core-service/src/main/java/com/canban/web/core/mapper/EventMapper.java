package com.canban.web.core.mapper;
import com.canban.api.core.EventDto;
import com.canban.web.core.entities.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventMapper {

    public EventDto entityToDto(Event event) {
        log.info(event.getEndDate().toString());
        return new EventDto(
                event.getId(),
                event.getTitle(),
                event.getContent(),
                event.getUsername(),
                event.getBeginDate(),
                event.getEndDate()
        );

    }

}