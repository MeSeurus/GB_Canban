package com.canban.web.core.services;

import com.canban.auth.entity.User;
import com.canban.auth.service.UserService;
import com.canban.web.core.dto.EventDetails;
import com.canban.web.core.entities.Event;
import com.canban.web.core.repositories.EventRepository;
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
        return eventRepository.findEventsByUserNickname(username);
    }

    public void createEvent(String username, EventDetails eventDetails) {
        Event event = Event.builder()
                .title(eventDetails.getTitle())
                .content(eventDetails.getContent())
//                .user(new User(username))
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