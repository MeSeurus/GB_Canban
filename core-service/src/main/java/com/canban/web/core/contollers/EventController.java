package com.canban.web.core.contollers;

import com.canban.api.core.EventDto;
import com.canban.api.errors.FieldsValidationError;
import com.canban.web.core.dto.EventDetailsForSearchRq;
import com.canban.web.core.dto.EventDetailsRq;
import com.canban.web.core.mapper.EventMapper;
import com.canban.web.core.services.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/events")
@CrossOrigin(origins = "http://localhost:3000/")
@RequiredArgsConstructor
@Tag(name = "События", description = "Методы работы с событиями")
@Slf4j
public class EventController {

    private final EventService eventService;

    private final EventMapper eventMapper;

    @Operation(
            summary = "Запрос на получение всех событий пользователя с возможностью фильтрации",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Page.class))
                    )
            }
    )
    @PostMapping("/search")
    public List<EventDto> searchAllEvents(
            @RequestHeader @Parameter(description = "Имя пользователя создателя", required = true) String username,
            @RequestBody @Parameter(description = "Модель для поиска событий", required = false) EventDetailsForSearchRq eventDetailsForSearchRq) {
        return eventService.searchAllEvents(username, eventDetailsForSearchRq)
                .stream()
                .map(eventMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @PostMapping()
    @Operation(
            summary = "Запрос на создание нового события",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Ошибка валидации", responseCode = "400",
                            content = @Content(schema = @Schema(implementation = FieldsValidationError.class))
                    )
            }
    )
    public void createEvent(@RequestHeader @Parameter(description = "Пользователь создатель", required = true) String username,
                            @RequestBody @Parameter(description = "Модель деталей события", required = true) EventDetailsRq eventDetailsRq) {
        eventService.createEvent(username, eventDetailsRq);
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

    @PostMapping("/{id}/{username}")
    @Operation(
            summary = "Запрос на доступ пользователя к событию",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )
    public void addUserToEvent(@PathVariable("id") @Parameter(description = "ID события", required = true) Long id,
                               @PathVariable("username") @Parameter(description = "Имя пользователя для получающего доступ", required = true) String usernameToAdd) {
        eventService.addUserToEvent(usernameToAdd, id);
    }

    @DeleteMapping("/{id}/{username}")
    @Operation(
            summary = "Запрос на удаления доступа пользователя к событию",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )
    public void removeUserFromEvent(@PathVariable("id") @Parameter(description = "ID события", required = true) Long id,
                                    @PathVariable("username") @Parameter(description = "Имя пользователя удаляемого из события", required = true) String usernameToRemove) {
        eventService.removeUserFromEvent(usernameToRemove, id);
    }

    @PatchMapping("/title")
    @Operation(
            summary = "Запрос на изменения названия события по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Событие не найдено", responseCode = "404"
                    )
            }
    )
    public void changeTitle(@RequestBody @Parameter(description = "Модель события", required = true) EventDto requestBody) {
        eventService.changeTitle(requestBody.getId(), requestBody.getTitle());
    }

    @PatchMapping("/content")
    @Operation(
            summary = "Запрос на изменения описания события по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Событие не найдено", responseCode = "404"
                    )
            }
    )
    public void changeContent(@RequestBody @Parameter(description = "Модель события", required = true) EventDto requestBody) {
        eventService.changeContent(requestBody.getId(), requestBody.getContent());
    }

    @PatchMapping("/begin_date")
    @Operation(
            summary = "Запрос на изменения начала события по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Событие не найдено", responseCode = "404"
                    )
            }
    )
    public void changeBeginDate(@RequestBody @Parameter(description = "Модель события", required = true) EventDto requestBody) {
        eventService.changeBeginDate(requestBody.getId(), requestBody.getBeginDate());
    }

    @PatchMapping("/end_date")
    @Operation(
            summary = "Запрос на изменения окончания события по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Событие не найдено", responseCode = "404"
                    )
            }
    )
    public void changeEndDate(@RequestBody @Parameter(description = "Модель события", required = true) EventDto requestBody) {
        eventService.changeEndDate(requestBody.getId(), requestBody.getEndDate());
    }

}