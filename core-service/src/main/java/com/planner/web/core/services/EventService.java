package com.planner.web.core.services;

import com.planner.web.core.dto.EventDetails;
import com.planner.web.core.entities.Event;
import com.planner.web.core.entities.User;
import com.planner.web.core.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final UserService userService;

    public List<Event> findEventsByDayId(Long dayId) {
        return eventRepository.findAllByDayId(dayId);
    }

    public List<Event> findEventsByUser(String username) {
        return eventRepository.findEventsByUser(username);
    }

    public void createEvent(String username, EventDetails eventDetails) {
        Event event = Event.builder()
                .title(eventDetails.getTitle())
                .content(eventDetails.getContent())
                .user(new User(username))
                .day(eventDetails.getDay())
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
