package com.canban.web.analytics.services;
import com.canban.api.analytics.EventsAnalyticsDtoWithList;
import com.canban.api.analytics.TasksAnalyticsDtoWithList;
import com.canban.web.analytics.integration.CoreIntegration;
import com.canban.web.analytics.mappers.TasksAnalyticsMapper;
import com.canban.web.analytics.repositories.TasksAnalyticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TasksAnalyticsService {

    private final TasksAnalyticsRepository tasksAnalyticsRepository;

    private final TasksAnalyticsMapper tasksAnalyticsMapper;

    private final CoreIntegration coreIntegration;

    @Scheduled(fixedRate = 3600000) // 3600000 - час, время в миллисекундах
    @Transactional
    public void askCoreForEventsAnalytics() {
        TasksAnalyticsDtoWithList tasksAnalyticsDtoWithList = coreIntegration.getTasksAnalyticsFromCore();
        tasksAnalyticsDtoWithList.getTasksAnalyticsDtoList()
                .stream().map(tasksAnalyticsMapper::dtoToEntity).collect(Collectors.toList())
                .stream().forEach(t -> tasksAnalyticsRepository.save(t));
    }


}