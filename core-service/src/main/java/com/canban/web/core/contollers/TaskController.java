package com.canban.web.core.contollers;

import com.canban.api.analytics.EventsAnalyticsDtoWithList;
import com.canban.api.analytics.TasksAnalyticsDtoWithList;
import com.canban.api.core.EventDto;
import com.canban.api.core.Priority;
import com.canban.api.core.State;
import com.canban.api.core.TaskDto;
import com.canban.api.errors.FieldsValidationError;
import com.canban.web.core.dto.TaskDetailsForSearchRq;
import com.canban.web.core.dto.TaskDetailsRq;
import com.canban.web.core.mapper.TaskAnalyticsMapper;
import com.canban.web.core.mapper.TaskMapper;
import com.canban.web.core.services.TaskService;
import com.canban.web.core.validators.TaskValidator;
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
@RequestMapping("/api/v1/tasks")
@CrossOrigin(origins = "http://localhost:3000/")
@RequiredArgsConstructor
@Tag(name = "Задачи", description = "Методы работы с задачами")
public class TaskController {

    private final TaskService taskService;

    private final TaskMapper taskMapper;

    private final TaskValidator taskValidator;

    @Operation(
            summary = "Запрос на получение всех задач по Id доски с возможностью фильтрации",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Page.class))
                    )
            }
    )
    @PostMapping
    public List<TaskDto> searchAllTasksByBoardId(
            @RequestBody @Parameter(description = "Модель для поиска задач", required = true) TaskDetailsForSearchRq taskDetailsForSearchRq
            ) {
        return taskService.searchAllTasksByBoardId(taskDetailsForSearchRq)
                .stream()
                .map(e -> taskMapper.entityToDtoWithCreator(e))
                .collect(Collectors.toList());
    }

    @PostMapping("/create")
    @Operation(
            summary = "Запрос на создание нового задания",
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
    public void createTask(@RequestHeader @Parameter(description = "Список пользователей", required = true) String username,
                           @RequestBody @Parameter(description = "Модель деталей задачи", required = true) TaskDetailsRq taskDetailsRq) {
        taskValidator.validate(taskDetailsRq);
        taskService.createTask(username, taskDetailsRq);
    }

    @PatchMapping("/change/title")
    @Operation(
            summary = "Запрос на изменения названия задачи по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Задача не найдена", responseCode = "404"
                    )
            }
    )
    public void changeTitle(@RequestBody @Parameter(description = "Модель задачи", required = true) TaskDto requestBody) {
        taskService.changeTitle(requestBody.getId(), requestBody.getContent());
    }

    @PatchMapping("/change/content")
    @Operation(
            summary = "Запрос на изменения описания задачи по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Задача не найдена", responseCode = "404"
                    )
            }
    )
    public void changeContent(@RequestBody @Parameter(description = "Модель задачи", required = true) TaskDto requestBody) {
        taskService.changeContent(requestBody.getId(), requestBody.getContent());
    }

    @PatchMapping("/change/user/executor")
    @Operation(
            summary = "Запрос на изменения исполнителя задачи по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Задача не найдена", responseCode = "404"
                    )
            }
    )
    public void changeUsernameExecutor(@RequestBody @Parameter(description = "Модель задачи", required = true) TaskDto requestBody) {
        taskValidator.validateUser(requestBody.getId());
        taskService.changeExecutorUsername(requestBody.getId(), requestBody.getContent());
    }

    @PatchMapping("/change/date/begin")
    @Operation(
            summary = "Запрос на изменения даты начала задачи по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Задача не найдена", responseCode = "404"
                    )
            }
    )
    public void changeBeginDate(@RequestBody @Parameter(description = "Модель задачи", required = true) TaskDto requestBody) {
        taskService.changeBeginDate(requestBody.getId(), requestBody.getBeginDate());
    }

    @PatchMapping("/change/date/end")
    @Operation(
            summary = "Запрос на изменения срока исполнения задачи по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Задача не найдена", responseCode = "404"
                    )
            }
    )
    public void changeEndDate(@RequestBody @Parameter(description = "Модель задачи", required = true) TaskDto requestBody) {
        taskService.changeEndDate(requestBody.getId(), requestBody.getEndDate());
    }

    @PatchMapping("/change/state")
    @Operation(
            summary = "Запрос на изменение статуса задачи по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Задача не найдена", responseCode = "404"
                    )
            }
    )
    public void changeState(@RequestBody @Parameter(description = "Модель задачи", required = true) TaskDto requestBody) {
        taskService.changeState(requestBody.getId(), requestBody.getState());
    }

    @PatchMapping("/change/priority")
    @Operation(
            summary = "Запрос на изменение приоритета задачи по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Задача не найдена", responseCode = "404"
                    )
            }
    )
    public void changePriority(@RequestBody @Parameter(description = "Модель задачи", required = true) TaskDto requestBody) {
        taskService.changePriority(requestBody.getId(), requestBody.getPriority());
    }

    @PatchMapping("/change/date/actual_end")
    @Operation(
            summary = "Запрос на изменение фактической даты выполнения задачи по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Задача не найдена", responseCode = "404"
                    )
            }
    )
    public void changeActualEndDate(@RequestBody @Parameter(description = "Модель задачи", required = true) TaskDto requestBody) {
        taskService.changeActualEndDate(requestBody.getId(), requestBody.getBeginDate());
    }

}
