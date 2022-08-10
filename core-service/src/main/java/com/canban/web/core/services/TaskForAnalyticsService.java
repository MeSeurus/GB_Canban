package com.canban.web.core.services;

import com.canban.web.core.entities.TaskForAnalytics;
import com.canban.web.core.repositories.TaskForAnalyticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskForAnalyticsService {

    private final TaskForAnalyticsRepository taskForAnalyticsRepository;

    public void createTaskForAnalytics(TaskForAnalytics taskForAnalytics) {
        taskForAnalyticsRepository.save(taskForAnalytics);
    }

    @Transactional
    public List<TaskForAnalytics> findAllForAnalytics() {
        List<TaskForAnalytics> taskForAnalytics = taskForAnalyticsRepository.findAll();
        taskForAnalyticsRepository.deleteAll();
        return taskForAnalytics;
    }

}
