package com.canban.web.core.services;

import com.canban.web.core.dto.EventDetailsRq;
import com.canban.web.core.entities.Event;
import com.canban.web.core.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public List<Event> findEventsByUser(String username) {
        return eventRepository.findEventsByUsername(username);
    }

    public void createEvent(String username, EventDetailsRq eventDetailsRq) {
        Event event = Event.builder()
                .title(eventDetailsRq.getTitle())
                .content(eventDetailsRq.getContent())
                .username(username)
                .beginDate(eventDetailsRq.getBeginDate())
                .endDate(eventDetailsRq.getEndDate())
                .build();
        eventRepository.save(event);
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }
}
