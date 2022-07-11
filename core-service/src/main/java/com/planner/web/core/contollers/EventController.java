package com.planner.web.core.contollers;

import com.planner.web.core.mapper.EventMapper;
import com.planner.web.core.dto.EventDetails;
import com.planner.web.api.core.EventDto;
import com.planner.web.core.services.EventService;
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
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
@Tag(name = "События", description = "Методы работы с событиями")
public class EventController {

    private final EventService eventService;
    private final EventMapper eventMapper;

    @Operation(
            summary = "Запрос на получение всех событий",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = List.class))
                    )
            }
    )
    @GetMapping()
    public List<EventDto> findAll() {
        return eventService.findAll().stream().map(e -> eventMapper.entityToDto(e)).collect(Collectors.toList());
    }

    @Operation(
            summary = "Запрос на получение события по id дня",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EventDto.class))
                    )
            }
    )
    @GetMapping("/day/{id}")
    public List<EventDto> findEventsByDayId(@PathVariable @Parameter(description = "Идентификатор события", required = true) Long id) {
        return eventService.findEventsByDayId(id).stream().map(e -> eventMapper.entityToDto(e)).collect(Collectors.toList());
    }

    /**
     * пока через PathVariable для простоты отладки через постман без фронта
     * todo переделать на RequestHeader
     */
    @GetMapping("/username/{username}")
    @Operation(
            summary = "Запрос на получение всех событий по имени пользователя",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EventDto.class))
                    )
            }
    )
    public List<EventDto> findEventsByUsername(@PathVariable @Parameter(description = "Имя пользователя", required = true) String username) {
        return eventService.findEventsByUser(username).stream().map(e -> eventMapper.entityToDto(e)).collect(Collectors.toList());
    }

    @PostMapping()
    @Operation(
            summary = "Запрос на создание нового события",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )
    public void createEvent(@RequestHeader @Parameter(description = "Список пользователей", required = true) String username, @RequestBody EventDetails eventDetails) {
        eventService.createEvent(username, eventDetails);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Запрос на удаление события по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )
    public void deleteDyId(@PathVariable @Parameter(description = "Идентификатор события", required = true) Long id) {
        eventService.deleteById(id);
    }


}
