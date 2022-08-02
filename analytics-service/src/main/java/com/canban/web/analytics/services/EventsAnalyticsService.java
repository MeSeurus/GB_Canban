package com.canban.web.analytics.services;

import com.canban.api.analytics.EventsAnalyticsDto;
import com.canban.api.analytics.EventsAnalyticsDtoWithList;
import com.canban.web.analytics.integration.CoreIntegration;
import com.canban.web.analytics.mappers.EventsAnalyticsMapper;
import com.canban.web.analytics.repositories.EventsAnalyticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EventsAnalyticsService {

    private final CoreIntegration coreIntegration;

    private final EventsAnalyticsRepository eventsAnalyticsRepository;

    private final EventsAnalyticsMapper eventsAnalyticsMapper;

    @Scheduled(fixedRate = 30000) // не помню надо читать
    @Transactional
    public void askCoreForEventsAnalytics() {
        EventsAnalyticsDtoWithList eventsAnalyticsDtoWithList = coreIntegration.getEventsAnalyticsFromCore();
        eventsAnalyticsDtoWithList.getEventsAnalyticsDtoList()
                .stream().map(eventsAnalyticsMapper::dtoToEntity).collect(Collectors.toList())
                .stream().forEach(e -> eventsAnalyticsRepository.save(e));
    }
}
