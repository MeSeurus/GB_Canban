package com.canban.web.core.contollers;

import com.canban.web.core.dto.KanbanBoardDetailsRq;
import com.canban.web.core.dto.KanbanBoardDetailsRs;
import com.canban.web.core.mapper.KanbanBoardMapper;
import com.canban.web.core.services.KanbanService;
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

    @GetMapping("/{id}")
    public KanbanBoardDetailsRs getBoard(@RequestHeader String username, @PathVariable("id") Long id) {
        return kanbanBoardMapper.entityToDto(kanbanService.getBoard(id));
    }

    @GetMapping()
    public List<KanbanBoardDetailsRs> getBoards(@RequestHeader String username) {
        return kanbanService.getBoards(username)
                .stream()
                .map(kanbanBoardMapper::entityToDto)
                .collect(Collectors.toList());

    }

    @PostMapping()
    public void createBoard(@RequestHeader String username, @RequestBody KanbanBoardDetailsRq kanbanBoardDetailsRq) {
        kanbanService.createBoard(username, kanbanBoardMapper.dtoToEntity(kanbanBoardDetailsRq));
    }




}
