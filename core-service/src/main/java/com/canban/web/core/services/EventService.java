package com.canban.web.core.services;

import com.canban.api.activemqevents.AddUserToEventGetEmailsEvent;
import com.canban.api.exceptions.ResourceNotFoundException;
import com.canban.web.core.config.JmsConfig;
import com.canban.web.core.dto.EventDetailsForSearchRq;
import com.canban.web.core.dto.EventDetailsRq;
import com.canban.web.core.entities.Event;
import com.canban.web.core.mapper.DateFormatter;
import com.canban.web.core.mapper.EventAnalyticsMapper;
import com.canban.web.core.repositories.EventRepository;
import com.canban.web.core.specification.EventSpecifications;
import com.canban.web.core.validators.EventValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    private final EventAnalyticsMapper eventAnalyticsMapper;

    private final DateFormatter dateFormatter;

    private final EventForAnalyticsService eventForAnalyticsService;

    private final EventValidator eventValidator;

    private final JmsTemplate jmsTemplate;

    public List<Event> searchAllEvents(String username,
                                       EventDetailsForSearchRq eventDetailsForSearchRq) {
        Specification<Event> spec = Specification.where(null);
        spec = spec.and(EventSpecifications.usernameEqual(username));
        spec = spec.or(EventSpecifications.userInUsersAdded(username));
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

    @Transactional
    public void createEvent(String username, EventDetailsRq eventDetailsRq) {
        eventValidator.validate(eventDetailsRq);
        Event event = Event.builder()
                .title(eventDetailsRq.getTitle())
                .content(eventDetailsRq.getContent())
                .username(username)
                .beginDate(eventDetailsRq.getBeginDate())
                .endDate(eventDetailsRq.getEndDate())
                .users(eventDetailsRq.getAddedUsers())
                .build();
        eventRepository.save(event);
        jmsTemplate.convertAndSend(JmsConfig.ADD_TO_EVENT_GET_EMAILS, new AddUserToEventGetEmailsEvent(username,event.getUsers(),event.getTitle()));
        eventForAnalyticsService.createEventForAnalytics(eventAnalyticsMapper.eventToEventForAnalytics(event));
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
        jmsTemplate.convertAndSend(JmsConfig.ADD_TO_EVENT_GET_EMAILS, new AddUserToEventGetEmailsEvent(username,users,event.getTitle()));
    }

    @Transactional
    public void removeUserFromEvent(String username, Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Не найдено событие с ID %d", id)));
        Set<String> users = event.getUsers();
        if (!users.remove(username)) {
            return;
        }
        event.setUsers(users);
        eventRepository.save(event);
    }

    @Transactional
    public void changeTitle(Long id, String title) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Невозможно поменять название события. Событие не найдено"));
        event.setTitle(title);
        eventForAnalyticsService.createEventForAnalytics(eventAnalyticsMapper.eventToEventForAnalytics(event));
    }

    @Transactional
    public void changeContent(Long id, String content) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Невозможно поменять содержимое события. Событие не найдено"));
        event.setContent(content);
        eventForAnalyticsService.createEventForAnalytics(eventAnalyticsMapper.eventToEventForAnalytics(event));
    }

    @Transactional
    public void changeBeginDate(Long id, LocalDateTime beginDate) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Невозможно поменять дату начала события. Событие не найдено"));
        event.setBeginDate(beginDate);
        eventForAnalyticsService.createEventForAnalytics(eventAnalyticsMapper.eventToEventForAnalytics(event));
    }

    @Transactional
    public void changeEndDate(Long id, LocalDateTime endDate) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Невозможно поменять дату окончания события. Событие не найдено"));
        event.setBeginDate(endDate);
        eventForAnalyticsService.createEventForAnalytics(eventAnalyticsMapper.eventToEventForAnalytics(event));
    }
}