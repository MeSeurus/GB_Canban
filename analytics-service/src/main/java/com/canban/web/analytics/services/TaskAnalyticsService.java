package com.canban.web.analytics.services;

import com.canban.api.analytics.TasksAnalyticsDtoWithList;
import com.canban.api.exceptions.ResourceNotFoundException;
import com.canban.web.analytics.dtos.AllStatisticsTaskAnalyticsRs;
import com.canban.web.analytics.entities.EventAnalytics;
import com.canban.web.analytics.entities.TaskAnalytics;
import com.canban.web.analytics.integration.CoreIntegration;
import com.canban.web.analytics.mappers.TaskAnalyticsMapper;
import com.canban.web.analytics.repositories.TaskAnalyticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskAnalyticsService {

    private final TaskAnalyticsRepository taskAnalyticsRepository;

    private final TaskAnalyticsMapper taskAnalyticsMapper;

    private final CoreIntegration coreIntegration;

    @Scheduled(fixedRate = 30000) // 3600000 - час, время в миллисекундах
    @Transactional
    public void askCoreForEventsAnalytics() {
        TasksAnalyticsDtoWithList tasksAnalyticsDtoWithList = coreIntegration.getTasksAnalyticsFromCore();
        taskAnalyticsRepository.saveAll(tasksAnalyticsDtoWithList.getTasksAnalyticsDtoList()
                .stream().map(taskAnalyticsMapper::dtoToEntity).collect(Collectors.toList()));
    }


    @Transactional
    public AllStatisticsTaskAnalyticsRs search(String username, LocalDateTime timeForSearch) {
        return new AllStatisticsTaskAnalyticsRs(
                taskAnalyticsMapper.entityToDto(taskAnalyticsRepository.searchTheLongestAndTheShortestCompletedTaskByUsernameAndByTimePeriod(username, timeForSearch, -1)
                        .orElseThrow(() -> new ResourceNotFoundException("Выполненные задачи за данный период времени не найдены"))),
                taskAnalyticsMapper.entityToDto(taskAnalyticsRepository.searchTheLongestAndTheShortestCompletedTaskByUsernameAndByTimePeriod(username, timeForSearch, 1)
                        .orElseThrow(() -> new ResourceNotFoundException("Выполненные задачи за данный период времени не найдены"))),
                taskAnalyticsRepository.getCountOfCompletedTasks(username, timeForSearch)
        );
    }

    public List<TaskAnalytics> findAll() {
        return taskAnalyticsRepository.findAll();
    }
}
