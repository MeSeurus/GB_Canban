package com.canban.web.core.contollers;

import com.canban.api.analytics.EventsAnalyticsDtoWithList;
import com.canban.web.core.mapper.EventAnalyticsMapper;
import com.canban.web.core.services.EventForAnalyticsService;
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
@RequestMapping("/api/v1/events/analytics")
@CrossOrigin(origins = "http://localhost:3000/")
@RequiredArgsConstructor
@Tag(name = "События для микросервиса аналитики", description = "Методы работы с событиями для микросервиса аналитики")
@Slf4j
public class EventForAnalyticsController {

    private final EventForAnalyticsService eventForAnalyticsService;

    private final EventAnalyticsMapper eventAnalyticsMapper;

    @GetMapping()
    @Operation(
            summary = "Запрос на получение всех событий всех пользователей для микросервиса аналитики за текущее время работы Core-MC",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EventsAnalyticsDtoWithList.class))
                    )
            }
    )
    public EventsAnalyticsDtoWithList findAllEventsForAnalytics() {
        return new EventsAnalyticsDtoWithList(eventForAnalyticsService.findAllForAnalytics()
                .stream()
                .map(eventAnalyticsMapper::entityToDto)
                .collect(Collectors.toList()));
    }

}
