package com.canban.web.analytics.controllers;

import com.canban.api.analytics.EventsAnalyticsDto;
import com.canban.api.exceptions.ResourceNotFoundException;
import com.canban.web.analytics.mappers.EventsAnalyticsMapper;
import com.canban.web.analytics.services.EventsAnalyticsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/events/analytics")
//@CrossOrigin(origins = "http://localhost:3000/")
@RequiredArgsConstructor
@Tag(name = "Аналитика событий", description = "Методы работы с аналитикой событий")
public class EventsAnalyticsController {

    private final EventsAnalyticsService eventsAnalyticsService;

    private final EventsAnalyticsMapper eventsAnalyticsMapper;

    /**
     * Метод для отладки
     * @return List<EventsAnalyticsDto>
     */
    @GetMapping()
    public List<EventsAnalyticsDto> findAll() {
        return eventsAnalyticsService.findAll().stream().map(e -> eventsAnalyticsMapper.entityToDto(e)).collect(Collectors.toList());
    }

    /**
     * Самый длинный ивент за последний месяц
     * @return EventsAnalyticsDto
     */
    @GetMapping("/month/long")
    public EventsAnalyticsDto getTheLongestEventForLastMonth(@RequestHeader String username) {
        return eventsAnalyticsMapper.entityToDto(
                eventsAnalyticsService.getTheLongestEventLastMonth(username)
                        .orElseThrow(() -> new ResourceNotFoundException("За последний месяц не было ивентов")));
    }

    /**
     * Самый длинный ивент за последнюю неделю
     * @return EventsAnalyticsDto
     */
    @GetMapping("/week/long")
    public EventsAnalyticsDto getTheLongestEventForLastWeek(@RequestHeader String username) {
        return eventsAnalyticsMapper.entityToDto(
                eventsAnalyticsService.getTheLongestEventLastWeek(username)
                        .orElseThrow(() -> new ResourceNotFoundException("За последнюю неделю не было ивентов")));
    }

    /**
     * Самый короткий ивент за последний месяц
     * @return EventsAnalyticsDto
     */
    @GetMapping("/month/short")
    public EventsAnalyticsDto getTheShortestEventForLastMonth(@RequestHeader String username) {
        return eventsAnalyticsMapper.entityToDto(
                eventsAnalyticsService.getTheShortestEventLastMonth(username)
                        .orElseThrow(() -> new ResourceNotFoundException("За последний месяц не было ивентов")));
    }

    /**
     * Самый короткий ивент за последнюю неделю
     * @return EventsAnalyticsDto
     */
    @GetMapping("/week/short")
    public EventsAnalyticsDto getTheShortestEventForLastWeek(@RequestHeader String username) {
        return eventsAnalyticsMapper.entityToDto(
                eventsAnalyticsService.getTheShortestEventLastWeek(username)
                        .orElseThrow(() -> new ResourceNotFoundException("За последнюю неделю не было ивентов")));
    }

    /**
     * Количество ивентов за последнюю неделю
     * @return Integer
     */
    @GetMapping("/week/count")
    public Integer getCountOfEventsForLastWeek(@RequestHeader String username) {
        return eventsAnalyticsService.getCountOfEventsLastWeek(username);
    }

    /**
     * Количество ивентов за последний месяц
     * @return Integer
     */
    @GetMapping("/month/count")
    public Integer getCountOfEventsForLastMonth(@RequestHeader String username) {
        return eventsAnalyticsService.getCountOfEventsLastMonth(username);
    }

}
