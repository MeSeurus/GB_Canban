package com.canban.web.core.contollers;

import com.canban.api.core.BoardToAddRq;
import com.canban.api.errors.AppError;
import com.canban.api.errors.FieldsValidationError;
import com.canban.web.core.dto.KanbanBoardDetailsRq;
import com.canban.web.core.dto.KanbanBoardDetailsRs;
import com.canban.web.core.mapper.KanbanBoardMapper;
import com.canban.web.core.services.KanbanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/kanban")
@CrossOrigin(origins = "http://localhost:3000/")
@RequiredArgsConstructor
@Tag(name = "Канбан-доски", description = "Методы работы с канбан-досками")
@Slf4j
public class KanbanController {

    private final KanbanService kanbanService;
    private final KanbanBoardMapper kanbanBoardMapper;

    /**
     * Метод для отладки
     *
     * @return Все суцществующие List<KanbanBoardDetailsRs>
     */
    @GetMapping("/all")
    public List<KanbanBoardDetailsRs> findAllBoards() {
        return kanbanService.getAllBoards()
                .stream()
                .map(kanbanBoardMapper::entityToDto)
                .collect(Collectors.toList());

    }

    @GetMapping()
    @Operation(
            summary = "Запрос на получение всех канбан-досок по имени пользователя",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = List.class))
                    )
            }
    )
    public List<KanbanBoardDetailsRs> getBoardsByUsername(@RequestHeader @Parameter(description = "Имя пользователя", required = true) String username) {
        return kanbanService.getBoardsByAddedUsername(username)
                .stream()
                .map(kanbanBoardMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @PostMapping()
    @Operation(
            summary = "Запрос на создание новой канбан-доски",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = List.class))
                    ),
                    @ApiResponse(
                            description = "Ошибка валидации", responseCode = "400",
                            content = @Content(schema = @Schema(implementation = FieldsValidationError.class))
                    )
            }
    )
    public void createBoard(@RequestHeader @Parameter(description = "Имя пользователя", required = true) String username,
                            @RequestBody @Parameter(description = "Модель для создания канбан-доски", required = true) KanbanBoardDetailsRq kanbanBoardDetailsRq) {
        kanbanService.createBoard(username, kanbanBoardDetailsRq);
    }

    @PostMapping("/add_user")
    @Operation(
            summary = "Запрос на добавление пользователя к канбан-доске",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = List.class))
                    ),
                    @ApiResponse(
                            description = "Ошибка добавления", responseCode = "403",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            }
    )
    public void addUserToKanbanBoard(@RequestHeader @Parameter(description = "Имя пользователя", required = true) String username,
                                     @RequestBody @Parameter(description = "Модель для добавления пользователя к канбан-доске", required = true) BoardToAddRq boardToAddRq) {
        kanbanService.addUserToKanbanBoard(username, boardToAddRq);
    }


}
