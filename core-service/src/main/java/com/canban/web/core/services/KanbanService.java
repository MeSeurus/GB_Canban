package com.canban.web.core.services;

import com.canban.api.exceptions.ResourceNotFoundException;
import com.canban.web.core.entities.KanbanBoard;
import com.canban.web.core.mapper.KanbanBoardMapper;
import com.canban.web.core.repositories.KanbanBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KanbanService {

    private final KanbanBoardRepository kanbanBoardRepository;
    private final KanbanBoardMapper kanbanBoardMapper;

    public void createBoard(String username, KanbanBoard kanbanBoard) {
        kanbanBoard.setCreator(username);
        kanbanBoardRepository.save(kanbanBoard);
    }

    public List<KanbanBoard> getBoards(String username) {
        return kanbanBoardRepository.findAllByCreator(username);
    }

    public KanbanBoard getBoard(Long id){
        return kanbanBoardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Не найдена доска с ID %d", id)));
    }
}
