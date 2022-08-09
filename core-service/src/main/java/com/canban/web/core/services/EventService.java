package com.canban.web.core.services;

import com.canban.api.exceptions.ResourceNotFoundException;
import com.canban.web.core.dto.EventDetailsForSearchRq;
import com.canban.web.core.dto.EventDetailsRq;
import com.canban.web.core.entities.Event;
import com.canban.web.core.mapper.DateFormatter;
import com.canban.web.core.repositories.EventRepository;
import com.canban.web.core.specification.EventSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    private final DateFormatter dateFormatter;

    private List<Event> events = new CopyOnWriteArrayList<>();

    public List<Event> searchAllEvents(String username,
                                       EventDetailsForSearchRq eventDetailsForSearchRq) {
        Specification<Event> spec = Specification.where(null);
        spec = spec.and(EventSpecifications.usernameEqual(username)); // обязательное поле
        if (eventDetailsForSearchRq.getTitlePart() != null) {
            spec = spec.and(EventSpecifications.titleLike(eventDetailsForSearchRq.getTitlePart()));
        }
        if (eventDetailsForSearchRq.getMaxBeginDate() != null) {
            spec = spec.and(EventSpecifications.beginDateLessOrEqualsThen(dateFormatter.stringToDate(eventDetailsForSearchRq.getMaxBeginDate())));
        }
        if (eventDetailsForSearchRq.getMinBeginDate() != null) {
            spec = spec.and(EventSpecifications.beginDateGreaterOrEqualsThen(dateFormatter.stringToDate(eventDetailsForSearchRq.getMinBeginDate())));
        }
        if (eventDetailsForSearchRq.getMaxEndDate() != null) {
            spec = spec.and(EventSpecifications.endDateLessOrEqualsThen(dateFormatter.stringToDate(eventDetailsForSearchRq.getMaxEndDate())));
        }
        if (eventDetailsForSearchRq.getMinEndDate() != null) {
            spec = spec.and(EventSpecifications.endDateGreaterOrEqualsThen(dateFormatter.stringToDate(eventDetailsForSearchRq.getMinEndDate())));
        }
        return eventRepository.findAll(spec);
    }

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

    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

//    @Transactional
//    public void addUserToEvent(String username, Long id) {
//        Event event = eventRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException(String.format("Не найдено событие с ID %d", id)));
//        Set<String> users = event.getUsers();
//        users.add(username);
//        event.setUsers(users);
//        eventRepository.save(event);
//        events.add(event);
//    }

//    @Transactional
//    public void removeUserFromEvent(String username, Long id) {
//        Event event = eventRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException(String.format("Не найдено событие с ID %d", id)));
//        Set<String> users = event.getUsers();
//        if (!users.remove(username)) {
//            return;
//        }
//        event.setUsers(users);
//        eventRepository.save(event);
//        events.add(event);
//    }

    @Transactional
    public void changeTitle(Long id, String title) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to change Event's title. Event not found"));
        event.setTitle(title);
    }

    @Transactional
    public void changeContent(Long id, String content) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to change Event's content. Event not found"));
        event.setContent(content);
    }

    @Transactional
    public void changeBeginDate(Long id, LocalDateTime beginDate) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to change Event's begin date. Event not found"));
        event.setBeginDate(beginDate);
    }

    @Transactional
    public void changeEndDate(Long id, LocalDateTime endDate) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to change Event's end date. Event not found"));
        event.setBeginDate(endDate);
    }
}