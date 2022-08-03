package com.canban.web.analytics.controllers;

import com.canban.api.analytics.EventsAnalyticsDto;
import com.canban.api.analytics.EventsAnalyticsDtoWithList;
import com.canban.api.exceptions.ResourceNotFoundException;
import com.canban.web.analytics.mappers.EventsAnalyticsMapper;
import com.canban.web.analytics.services.EventsAnalyticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/events/analytics")
@CrossOrigin(origins = "http://localhost:3000/")
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
    @Operation(
            summary = "Запрос на получение самого длинного события пользователя за последний месяц по имени пользователя",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EventsAnalyticsDto.class))
                    ),
                    @ApiResponse(
                            description = "Ошибка получения", responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ResourceNotFoundException.class))
                    )
            }
    )
    public EventsAnalyticsDto getTheLongestEventForLastMonth(@RequestHeader @Parameter(description = "Имя пользователя", required = true) String username) {
        return eventsAnalyticsMapper.entityToDto(
                eventsAnalyticsService.getTheLongestEventLastMonth(username)
                        .orElseThrow(() -> new ResourceNotFoundException("За последний месяц не было ивентов")));
    }

    /**
     * Самый длинный ивент за последнюю неделю
     * @return EventsAnalyticsDto
     */
    @GetMapping("/week/long")
    @Operation(
            summary = "Запрос на получение самого длинного события пользователя за последнюю неделю по имени пользователя",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EventsAnalyticsDto.class))
                    ),
                    @ApiResponse(
                            description = "Ошибка получения", responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ResourceNotFoundException.class))
                    )
            }
    )
    public EventsAnalyticsDto getTheLongestEventForLastWeek(@RequestHeader @Parameter(description = "Имя пользователя", required = true) String username) {
        return eventsAnalyticsMapper.entityToDto(
                eventsAnalyticsService.getTheLongestEventLastWeek(username)
                        .orElseThrow(() -> new ResourceNotFoundException("За последнюю неделю не было ивентов")));
    }

    /**
     * Самый короткий ивент за последний месяц
     * @return EventsAnalyticsDto
     */
    @GetMapping("/month/short")
    @Operation(
            summary = "Запрос на получение самого короткого события пользователя за последний месяц по имени пользователя",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EventsAnalyticsDto.class))
                    ),
                    @ApiResponse(
                            description = "Ошибка получения", responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ResourceNotFoundException.class))
                    )
            }
    )
    public EventsAnalyticsDto getTheShortestEventForLastMonth(@RequestHeader @Parameter(description = "Имя пользователя", required = true) String username) {
        return eventsAnalyticsMapper.entityToDto(
                eventsAnalyticsService.getTheShortestEventLastMonth(username)
                        .orElseThrow(() -> new ResourceNotFoundException("За последний месяц не было ивентов")));
    }

    /**
     * Самый короткий ивент за последнюю неделю
     * @return EventsAnalyticsDto
     */
    @GetMapping("/week/short")
    @Operation(
            summary = "Запрос на получение самого короткого события пользователя за последний месяц по имени пользователя",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EventsAnalyticsDto.class))
                    ),
                    @ApiResponse(
                            description = "Ошибка получения", responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ResourceNotFoundException.class))
                    )
            }
    )
    public EventsAnalyticsDto getTheShortestEventForLastWeek(@RequestHeader @Parameter(description = "Имя пользователя", required = true) String username) {
        return eventsAnalyticsMapper.entityToDto(
                eventsAnalyticsService.getTheShortestEventLastWeek(username)
                        .orElseThrow(() -> new ResourceNotFoundException("За последнюю неделю не было ивентов")));
    }

    /**
     * Количество ивентов за последнюю неделю
     * @return Integer
     */
    @GetMapping("/week/count")
    @Operation(
            summary = "Запрос на получение количества событий произошедших за последнюю неделю по имени пользователя",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EventsAnalyticsDto.class))
                    ),
                    @ApiResponse(
                            description = "Ошибка получения", responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ResourceNotFoundException.class))
                    )
            }
    )
    public Integer getCountOfEventsForLastWeek(@RequestHeader @Parameter(description = "Имя пользователя", required = true) String username) {
        return eventsAnalyticsService.getCountOfEventsLastWeek(username);
    }

    /**
     * Количество ивентов за последний месяц
     * @return Integer
     */
    @GetMapping("/month/count")
    @Operation(
            summary = "Запрос на получение количества событий произошедших за последний месяц по имени пользователя",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EventsAnalyticsDto.class))
                    ),
                    @ApiResponse(
                            description = "Ошибка получения", responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ResourceNotFoundException.class))
                    )
            }
    )
    public Integer getCountOfEventsForLastMonth(@RequestHeader @Parameter(description = "Имя пользователя", required = true) String username) {
        return eventsAnalyticsService.getCountOfEventsLastMonth(username);
    }

}
