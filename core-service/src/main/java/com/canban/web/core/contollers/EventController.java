package com.canban.web.core.contollers;

import com.canban.web.core.mapper.EventMapper;
import com.canban.web.core.dto.EventDetailsRq;
import com.canban.api.core.EventDto;
import com.canban.web.core.services.EventService;
import com.canban.web.core.validators.EventValidator;
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
@CrossOrigin(origins = "http://localhost:3000/")
@RequiredArgsConstructor
@Tag(name = "События", description = "Методы работы с событиями")
public class EventController {

    private final EventValidator eventValidator;
    private final EventService eventService;
    private final EventMapper eventMapper;

    @GetMapping()
    @Operation(
            summary = "Запрос на получение всех событий по имени пользователя",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EventDto.class))
                    )
            }
    )
    public List<EventDto> findEventsByUsername(@RequestHeader @Parameter(description = "Имя пользователя", required = true) String username) {
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
    public void createEvent(@RequestHeader @Parameter(description = "Список пользователей", required = true) String username, @RequestBody EventDetailsRq eventDetails) {
        eventValidator.validate(eventDetails);
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
