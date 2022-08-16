package com.canban.web.analytics.services;

import com.canban.api.analytics.EventsAnalyticsDtoWithList;
import com.canban.api.exceptions.ResourceNotFoundException;
import com.canban.web.analytics.dtos.AllStatisticsEventAnalyticsRs;
import com.canban.web.analytics.entities.EventAnalytics;
import com.canban.web.analytics.integration.CoreIntegration;
import com.canban.web.analytics.mappers.EventAnalyticsMapper;
import com.canban.web.analytics.repositories.EventAnalyticsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class EventAnalyticsService {

    private final CoreIntegration coreIntegration;

    private final EventAnalyticsRepository eventAnalyticsRepository;

    private final EventAnalyticsMapper eventAnalyticsMapper;

    @Scheduled(cron = "0 0 4 * * *")
    @Transactional
    public void askCoreForEventsAnalytics() {
        EventsAnalyticsDtoWithList eventsAnalyticsDtoWithList = coreIntegration.getEventsAnalyticsFromCore();
        eventAnalyticsRepository.saveAll(eventsAnalyticsDtoWithList.getEventsAnalyticsDtoList()
                .stream().map(eventAnalyticsMapper::dtoToEntity).collect(Collectors.toList()));
    }

    @Transactional
    public AllStatisticsEventAnalyticsRs search(String username, LocalDateTime timeForSearch) {
        return new AllStatisticsEventAnalyticsRs(
                eventAnalyticsRepository.searchTheLongestCompletedEventByUsernameAndByTimePeriod(username, timeForSearch)
                        .orElseThrow(() -> new ResourceNotFoundException("Выполненные события за данный период времени не найдены"))
                        .getEventTitle(),
                eventAnalyticsRepository.searchTheShortestCompletedEventByUsernameAndByTimePeriod(username, timeForSearch)
                        .orElseThrow(() -> new ResourceNotFoundException("Выполненные события за данный период времени не найдены"))
                        .getEventTitle(),
                eventAnalyticsRepository.getCountOfCompletedEvents(username, timeForSearch)
        );
    }

    public List<EventAnalytics> findAll() {
        return eventAnalyticsRepository.findAll();
    }
}
