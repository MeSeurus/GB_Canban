package com.canban.web.analytics.controllers;

import com.canban.api.analytics.EventsAnalyticsDtoWithList;
import com.canban.api.exceptions.ResourceNotFoundException;
import com.canban.web.analytics.dtos.AllStatisticsEventsAnalyticsRs;
import com.canban.web.analytics.services.TasksAnalyticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/tasks/analytics")
@CrossOrigin(origins = "http://localhost:3000/")
@RequiredArgsConstructor
@Tag(name = "Аналитика задач", description = "Методы работы с аналитикой задач")
public class TasksAnalyticsController {

    private final TasksAnalyticsService tasksAnalyticsService;

//    @GetMapping
//    @Operation(
//            summary = "Запрос на получение всей аналитики событий по имени пользователя за определенный срок",
//            responses = {
//                    @ApiResponse(
//                            description = "Успешный ответ", responseCode = "200",
//                            content = @Content(schema = @Schema(implementation = EventsAnalyticsDtoWithList.class))
//                    ),
//                    @ApiResponse(
//                            description = "Аналитика задач не найдена", responseCode = "404",
//                            content = @Content(schema = @Schema(implementation = ResourceNotFoundException.class))
//                    )
//            }
//    )
//    public AllStatisticsEventsAnalyticsRs searchByUsernameByDate(
//            @RequestHeader @Parameter(description = "Имя пользователя", required = true) String username,
//            @RequestParam @Parameter(description = "Дата начала отсчета аналитики", required = true) LocalDateTime timeForSearch) {
//        return tasksAnalyticsService.search(username, timeForSearch);
//    }


}
