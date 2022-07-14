package com.canban.web.core.contollers;

import com.canban.api.core.TaskDto;
import com.canban.web.core.mapper.TaskMapper;
import com.canban.web.core.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Tag(name = "Задачи", description = "Методы работы с задачами")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @GetMapping()
    @Operation(
            summary = "Запрос на получение всех событий по имени пользователя",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TaskDto.class))
                    )
            }
    )
    public List<TaskDto> findEventsByUsername(@RequestHeader @Parameter(description = "Имя пользователя", required = true) String username) {
        return taskService.findTaskByUser(username).stream().map(taskMapper::entityToDto).collect(Collectors.toList());
    }
}
