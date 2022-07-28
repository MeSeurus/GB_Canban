package com.canban.web.core.services;

import com.canban.api.exceptions.ResourceNotFoundException;
import com.canban.web.core.dto.EventDetailsRq;
import com.canban.web.core.entities.Event;
import com.canban.web.core.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

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
                .build();
        eventRepository.save(event);
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    @Transactional
    public void addUserToEvent(String username, Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Не найдено событие с ID %d", id)));
        Set<String> users = event.getUsers();
        users.add(username);
        event.setUsers(users);
        eventRepository.save(event);
    }

    @Transactional
    public void removeUserFromEvent(String username, Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Не найдено событие с ID %d", id)));
        Set<String> users = event.getUsers();
        if(!users.remove(username)){
            return;
        };
        event.setUsers(users);
        eventRepository.save(event);
    }
}
