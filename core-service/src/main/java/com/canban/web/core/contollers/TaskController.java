package com.canban.web.core.contollers;

import com.canban.api.analytics.EventsAnalyticsDtoWithList;
import com.canban.api.analytics.TasksAnalyticsDtoWithList;
import com.canban.api.core.EventDto;
import com.canban.api.core.Priority;
import com.canban.api.core.State;
import com.canban.api.core.TaskDto;
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
import org.springframework.data.domain.Page;
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
            summary = "Запрос на получение всех задач по Id доски",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Page.class))
                    )
            }
    )
    @PostMapping
    public List<TaskDto> searchAllTasksByBoardId(
            @RequestBody @Parameter(description = "Дто для фильтрации", required = true) TaskDetailsForSearchRq taskDetailsForSearchRq
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
                    )
            }
    )
    public void createTask(@RequestHeader @Parameter(description = "Список пользователей", required = true) String username, @RequestBody TaskDetailsRq taskDetailsRq) {
        taskValidator.validate(taskDetailsRq);
        taskService.createTask(username, taskDetailsRq);
    }

    @PatchMapping("/change/title")
    public void changeTitle(@RequestBody TaskDto requestBody) {
        taskService.changeTitle(requestBody.getId(), requestBody.getContent());
    }

    @PatchMapping("/change/content")
    public void changeContent(@RequestBody TaskDto requestBody) {
        taskService.changeContent(requestBody.getId(), requestBody.getContent());
    }

    @PatchMapping("/change/user_executor")
    public void changeUsernameExecutor(@RequestBody TaskDto requestBody) {
        taskValidator.validateUser(requestBody.getId());
        taskService.changeExecutorUsername(requestBody.getId(), requestBody.getContent());
    }

    @PatchMapping("/change/begin_date")
    public void changeBeginDate(@RequestBody TaskDto requestBody) {
        taskService.changeBeginDate(requestBody.getId(), requestBody.getBeginDate());
    }
    @PatchMapping("/change/end_date")
    public void changeEndDate(@RequestBody TaskDto requestBody) {
        taskService.changeEndDate(requestBody.getId(), requestBody.getEndDate());
    }

    @PatchMapping("/change/state")
    public void changeState(@RequestBody TaskDto requestBody) {
        taskService.changeState(requestBody.getId(), State.valueOf(requestBody.getState()));
    }

    @PatchMapping("/change/priority")
    public void changePriority(@RequestBody TaskDto requestBody) {
        taskService.changePriority(requestBody.getId(), Priority.valueOf(requestBody.getPriority()));
    }

    @PatchMapping("/change/actual_end_date")
    public void changeActualEndDate(@RequestBody TaskDto requestBody) {
        taskService.changeActualEndDate(requestBody.getId(), requestBody.getBeginDate());
    }

}
