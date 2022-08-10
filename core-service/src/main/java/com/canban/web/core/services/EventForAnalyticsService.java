package com.canban.web.core.services;

import com.canban.web.core.entities.EventForAnalytics;
import com.canban.web.core.repositories.EventForAnalyticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventForAnalyticsService {

    private final EventForAnalyticsRepository eventForAnalyticsRepository;

    public void createEventForAnalytics(EventForAnalytics eventForAnalytics) {
        eventForAnalyticsRepository.save(eventForAnalytics);
    }

    @Transactional
    public List<EventForAnalytics> findAllForAnalytics() {
        List<EventForAnalytics> eventForAnalytics = eventForAnalyticsRepository.findAll();
        eventForAnalyticsRepository.deleteAll();
        return eventForAnalytics;
    }


}
