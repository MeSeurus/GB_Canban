package com.canban.web.analytics.services;

import com.canban.api.analytics.EventsAnalyticsDtoWithList;
import com.canban.api.exceptions.ResourceNotFoundException;
import com.canban.web.analytics.dtos.AllStatisticsEventsAnalyticsRs;
import com.canban.web.analytics.integration.CoreIntegration;
import com.canban.web.analytics.mappers.EventsAnalyticsMapper;
import com.canban.web.analytics.repositories.EventsAnalyticsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class EventsAnalyticsService {

    private final CoreIntegration coreIntegration;

    private final EventsAnalyticsRepository eventsAnalyticsRepository;

    private final EventsAnalyticsMapper eventsAnalyticsMapper;

    @Scheduled(fixedRate = 30000) // 3600000 - час, время в миллисекундах
    @Transactional
    public void askCoreForEventsAnalytics() {
        EventsAnalyticsDtoWithList eventsAnalyticsDtoWithList = coreIntegration.getEventsAnalyticsFromCore();
        eventsAnalyticsDtoWithList.getEventsAnalyticsDtoList()
                .stream().map(eventsAnalyticsMapper::dtoToEntity).collect(Collectors.toList())
                .stream().forEach(e -> eventsAnalyticsRepository.save(e));
    }

    @Transactional
    public AllStatisticsEventsAnalyticsRs search(String username, LocalDateTime timeForSearch) {
        return new AllStatisticsEventsAnalyticsRs(
                eventsAnalyticsMapper.entityToDto(eventsAnalyticsRepository.searchTheLongestAndTheShortestCompletedEventByUsernameAndByTimePeriod(username, timeForSearch, -1)
                        .orElseThrow(() -> new ResourceNotFoundException("Выполненные события за данный период времени не найдены"))),
                eventsAnalyticsMapper.entityToDto(eventsAnalyticsRepository.searchTheLongestAndTheShortestCompletedEventByUsernameAndByTimePeriod(username, timeForSearch, 1)
                        .orElseThrow(() -> new ResourceNotFoundException("Выполненные события за данный период времени не найдены"))),
                eventsAnalyticsRepository.getCountOfCompletedEvents(username, timeForSearch)
        );
    }

}
