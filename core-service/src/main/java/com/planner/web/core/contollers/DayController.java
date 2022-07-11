package com.planner.web.core.contollers;

import com.planner.web.api.exceptions.ResourceNotFoundException;
import com.planner.web.core.mapper.DayMapper;
import com.planner.web.api.core.DayDto;
import com.planner.web.core.entities.Day;
import com.planner.web.core.services.DayService;
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
@RequestMapping("/api/v1/days")
@RequiredArgsConstructor
@Tag(name = "Дни", description = "Методы работы с днями")
public class DayController {

    private final DayService dayService;
    private final DayMapper dayMapper;

    @Operation(
            summary = "Запрос на получение всех дней",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = List.class))
                    )
            }
    )
    @GetMapping
    public List<DayDto> findAll() {
        return dayService.findAll().stream().map(d -> dayMapper.entityToDto(d)).collect(Collectors.toList());
    }

    @Operation(
            summary = "Запрос на получение дня по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = DayDto.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public DayDto findById(@PathVariable @Parameter(description = "Идентификатор дня", required = true) Long id) {
        return dayMapper.entityToDto(dayService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Day not found, day id = " + id)));
    }

    @Operation(
            summary = "Запрос на создание нового дня"
    )
    @PostMapping
    public void createNew(@RequestHeader DayDto dayDto) {
        Day day = dayMapper.dtoToEntity(dayDto);
        dayService.createNew(day);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Запрос на удаление дня по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )
    public void deleteById(@PathVariable @Parameter(description = "Идентификатор дня", required = true) Long id) {
        dayService.deleteById(id);
    }


}
