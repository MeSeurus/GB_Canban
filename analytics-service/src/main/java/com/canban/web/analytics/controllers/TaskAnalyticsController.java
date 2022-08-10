package com.canban.web.analytics.controllers;

import com.canban.api.analytics.EventsAnalyticsDtoWithList;
import com.canban.api.errors.AppError;
import com.canban.web.analytics.dtos.AllStatisticsTaskAnalyticsRs;
import com.canban.web.analytics.dtos.DateDto;
import com.canban.web.analytics.services.TaskAnalyticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks/analytics")
@CrossOrigin(origins = "http://localhost:3000/")
@RequiredArgsConstructor
@Tag(name = "Аналитика задач", description = "Методы работы с аналитикой задач")
public class TaskAnalyticsController {

    private final TaskAnalyticsService taskAnalyticsService;

    @PostMapping
    @Operation(
            summary = "Запрос на получение всей аналитики завершенных задач по имени пользователя за определенный срок",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EventsAnalyticsDtoWithList.class))
                    ),
                    @ApiResponse(
                            description = "Аналитика задач не найдена", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            }
    )
    public AllStatisticsTaskAnalyticsRs searchAllAnalyticsByUsernameAndByDate(
            @RequestHeader @Parameter(description = "Имя пользователя по которому выбирается аналитика", required = true) String username,
            @RequestBody @Parameter(description = "Дто для выборки аналитики", required = true) DateDto dateDto) {
        return taskAnalyticsService.search(username, dateDto.getStartDate());
    }


}
