package com.canban.web.analytics.controllers;

import com.canban.api.analytics.EventsAnalyticsDto;
import com.canban.api.exceptions.ResourceNotFoundException;
import com.canban.web.analytics.services.TasksAnalyticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks/analytics")
@CrossOrigin(origins = "http://localhost:3000/")
@RequiredArgsConstructor
@Tag(name = "Аналитика задач", description = "Методы работы с аналитикой задач")
public class TasksAnalyticsController {

    private final TasksAnalyticsService tasksAnalyticsService;


}
