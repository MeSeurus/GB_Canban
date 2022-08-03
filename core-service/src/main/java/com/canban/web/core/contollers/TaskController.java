package com.canban.web.core.contollers;

import com.canban.api.core.TaskDto;
import com.canban.web.core.dto.TaskDetailsRq;
import com.canban.web.core.enums.Priority;
import com.canban.web.core.enums.State;
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

    @GetMapping()
    @Operation(
            summary = "Запрос на получение всех заданий по имени пользователя",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TaskDto.class))
                    )
            }
    )
    public List<TaskDto> findEventsByUsername(@RequestHeader @Parameter(description = "Имя пользователя", required = true) String username) {
        return taskService.findTaskByUsername(username).stream().map(taskMapper::entityToDto).collect(Collectors.toList());
    }

    @PostMapping()
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

    @PatchMapping("/change/username")
    public void changeUsername(@RequestBody TaskDto requestBody) {
        taskValidator.validateUser(requestBody.getId());
        taskService.changeUsername(requestBody.getId(), requestBody.getContent());
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

    @PatchMapping("/change/kanban_name")
    public void changeKanbanName(@RequestBody TaskDto requestBody) {
        taskService.changeKanbanName(requestBody.getId(), requestBody.getKanbanName());
    }
}
