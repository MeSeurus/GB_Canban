package com.canban.web.analytics.controllers;

import com.canban.web.analytics.services.TasksAnalyticsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tasks/analytics")
@CrossOrigin(origins = "http://localhost:3000/")
@RequiredArgsConstructor
@Tag(name = "Аналитика задач", description = "Методы работы с аналитикой задач")
public class TasksAnalyticsController {

    private final TasksAnalyticsService tasksAnalyticsService;


}
