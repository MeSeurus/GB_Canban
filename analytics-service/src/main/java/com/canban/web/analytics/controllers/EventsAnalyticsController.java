package com.canban.web.analytics.controllers;

import com.canban.api.analytics.EventsAnalyticsDto;
import com.canban.api.exceptions.ResourceNotFoundException;
import com.canban.web.analytics.mappers.EventsAnalyticsMapper;
import com.canban.web.analytics.services.EventsAnalyticsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/events/analytics")
//@CrossOrigin(origins = "http://localhost:3000/")
@RequiredArgsConstructor
@Tag(name = "Аналитика событий", description = "Методы работы с аналитикой событий")
public class EventsAnalyticsController {

    private final EventsAnalyticsService eventsAnalyticsService;

    private final EventsAnalyticsMapper eventsAnalyticsMapper;

    /**
     * Самый длинный ивент за последний месяц
     * @return EventsAnalyticsDto
     */
    @GetMapping("/month")
    public EventsAnalyticsDto getTheLongestForLastMonth() {
        return eventsAnalyticsMapper.entityToDto(
                eventsAnalyticsService.getTheLongestEventLastMonth()
                        .orElseThrow(() -> new ResourceNotFoundException("За последний месяц не было ивентов")));
    }

    /**
     * Самый длинный ивент за последнюю неделю
     * @return EventsAnalyticsDto
     */
    @GetMapping("/week")
    public EventsAnalyticsDto getTheLongestForLastWeek() {
        return eventsAnalyticsMapper.entityToDto(
                eventsAnalyticsService.getTheLongestEventLastWeek()
                        .orElseThrow(() -> new ResourceNotFoundException("За последнюю неделю не было ивентов")));
    }

}
