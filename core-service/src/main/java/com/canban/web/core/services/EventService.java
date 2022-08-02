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
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    private List<Event> events = new CopyOnWriteArrayList<>();

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
        events.add(event);
    }

    public List<Event> findAllForAnalytics() {
        return events;
    }

    public void clearList() {
        events.clear();
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
        events.add(event);
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
        events.add(event);
    }
}