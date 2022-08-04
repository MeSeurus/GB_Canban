package com.canban.web.core.contollers;

import com.canban.api.core.BoardToAddRq;
import com.canban.web.core.dto.KanbanBoardDetailsRq;
import com.canban.web.core.dto.KanbanBoardDetailsRs;
import com.canban.web.core.mapper.KanbanBoardMapper;
import com.canban.web.core.services.KanbanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@Slf4j
public class KanbanController {

    private final KanbanService kanbanService;
    private final KanbanBoardMapper kanbanBoardMapper;

    /**
     * Метод для отладки
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
            summary = "Запрос на получение всех событий всех канбан-досок по имени пользователя",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = List.class))
                    )
            }
    )
    public List<KanbanBoardDetailsRs> getBoardsByUsername(@RequestHeader String username) {
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
                    )
            }
    )
    public void createBoard(@RequestHeader String username, @RequestBody KanbanBoardDetailsRq kanbanBoardDetailsRq) {
        kanbanService.createBoard(username, kanbanBoardDetailsRq.getTitle());
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
                            content = @Content(schema = @Schema(implementation = ResponseEntity.class))
                    )
            }
    )
    public void addUserToKanbanBoard(@RequestHeader String username, @RequestBody BoardToAddRq boardToAddRq) {
       kanbanService.addUserToKanbanBoard(username, boardToAddRq);
    }


}
