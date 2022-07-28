package com.canban.web.core.services;

import com.canban.api.exceptions.ResourceNotFoundException;
import com.canban.web.core.dto.EventDetailsRq;
import com.canban.web.core.dto.KanbanBoardDetailsRq;
import com.canban.web.core.dto.KanbanBoardDetailsRs;
import com.canban.web.core.entities.Event;
import com.canban.web.core.entities.KanbanBoard;
import com.canban.web.core.mapper.KanbanBoardMapper;
import com.canban.web.core.repositories.EventRepository;
import com.canban.web.core.repositories.KanbanBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KanbanService {

    private final KanbanBoardRepository kanbanBoardRepository;
    private final KanbanBoardMapper kanbanBoardMapper;

    public void createBoard(String username, KanbanBoardDetailsRq kanbanBoardDetailsRq) {
        KanbanBoard kanbanBoard = kanbanBoardMapper.dtoToEntity(kanbanBoardDetailsRq);
        kanbanBoard.setCreator(username);
        kanbanBoardRepository.save(kanbanBoard);
    }

    public List<KanbanBoardDetailsRs> getBoards(String username) {
        return kanbanBoardRepository.findAllByCreator(username)
                .stream()
                .map(kanbanBoardMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public KanbanBoardDetailsRs getBoard(Long id){
        return kanbanBoardMapper.entityToDto(kanbanBoardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Не найдена доска с ID %d", id))));
    }
}
