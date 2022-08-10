package com.canban.web.analytics.controllers;

import com.canban.api.analytics.EventsAnalyticsDtoWithList;
import com.canban.api.errors.AppError;
import com.canban.api.exceptions.ResourceNotFoundException;
import com.canban.web.analytics.dtos.AllStatisticsEventsAnalyticsRs;
import com.canban.web.analytics.dtos.DateDto;
import com.canban.web.analytics.services.EventsAnalyticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDateTime;


@RestController
@RequestMapping("/api/v1/events/analytics")
@CrossOrigin(origins = "http://localhost:3000/")
@RequiredArgsConstructor
@Tag(name = "Аналитика событий", description = "Методы работы с аналитикой событий")
@Slf4j
public class EventsAnalyticsController {

    private final EventsAnalyticsService eventsAnalyticsService;

    @PostMapping
    @Operation(
            summary = "Запрос на получение аналитики завершенных событий по имени пользователя за определенный срок",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EventsAnalyticsDtoWithList.class))
                    ),
                    @ApiResponse(
                            description = "Аналитика событий не найдена", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            }
    )
    public AllStatisticsEventsAnalyticsRs searchAllAnalyticsByUsernameAndByDate(
            @RequestHeader @Parameter(description = "Имя пользователя", required = true) String username,
            @RequestBody @Parameter(description = "Дата начала отсчета аналитики", required = true) DateDto dateDto) {
        return eventsAnalyticsService.search(username, dateDto.getStartDate());
    }


}
