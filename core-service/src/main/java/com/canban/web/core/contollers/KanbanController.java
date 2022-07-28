package com.canban.web.core.contollers;

import com.canban.web.core.dto.KanbanBoardDetailsRq;
import com.canban.web.core.dto.KanbanBoardDetailsRs;
import com.canban.web.core.mapper.EventMapper;
import com.canban.web.core.dto.EventDetailsRq;
import com.canban.api.core.EventDto;
import com.canban.web.core.mapper.KanbanBoardMapper;
import com.canban.web.core.services.EventService;
import com.canban.web.core.services.KanbanService;
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
@RequestMapping("/api/v1/kanban")
@CrossOrigin(origins = "http://localhost:3000/")
@RequiredArgsConstructor
public class KanbanController {

    private final KanbanService kanbanService;
    private final KanbanBoardMapper kanbanBoardMapper;

    @GetMapping("/boards/{id}")
    public KanbanBoardDetailsRs getBoard(@RequestHeader String username, @PathVariable("id") Long id) {
        return kanbanService.getBoard(id);
    }

    @GetMapping("/boards")
    public List<KanbanBoardDetailsRs> getBoards(@RequestHeader String username) {
        return kanbanService.getBoards(username);
    }

    @PostMapping("/boards")
    public void createBoard(@RequestHeader String username, @RequestBody KanbanBoardDetailsRq kanbanBoardDetailsRq) {
        kanbanService.createBoard(username, kanbanBoardDetailsRq);
    }




}
