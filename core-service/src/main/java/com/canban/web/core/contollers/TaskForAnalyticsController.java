package com.canban.web.core.contollers;

import com.canban.api.analytics.EventsAnalyticsDtoWithList;
import com.canban.api.analytics.TasksAnalyticsDtoWithList;
import com.canban.web.core.mapper.TaskAnalyticsMapper;
import com.canban.web.core.services.TaskForAnalyticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/tasks/analytics")
@CrossOrigin(origins = "http://localhost:3000/")
@RequiredArgsConstructor
@Tag(name = "Задачи для микросервиса аналитики", description = "Методы работы с задачами для микросервиса аналитики")
@Slf4j
public class TaskForAnalyticsController {

    private final TaskForAnalyticsService taskForAnalyticsService;

    private final TaskAnalyticsMapper taskAnalyticsMapper;

    @GetMapping()
    @Operation(
            summary = "Запрос на получение всех задач всех пользователей для микросервиса аналитики за текущее время работы Core-MC",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EventsAnalyticsDtoWithList.class))
                    )
            }
    )
    public TasksAnalyticsDtoWithList findAllTasksForAnalytics() {
        return new TasksAnalyticsDtoWithList(taskForAnalyticsService.findAllForAnalytics()
                .stream()
                .map(taskAnalyticsMapper::entityToDto)
                .collect(Collectors.toList()));
    }

}
