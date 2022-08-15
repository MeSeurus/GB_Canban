package com.canban.web.core.services;

import com.canban.api.core.BoardToAddRq;
import com.canban.api.exceptions.ForbiddenException;
import com.canban.api.exceptions.ResourceNotFoundException;
import com.canban.web.core.dto.KanbanBoardDetailsRq;
import com.canban.web.core.entities.KanbanBoard;
import com.canban.web.core.repositories.KanbanBoardRepository;
import com.canban.web.core.validators.KanbanValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class KanbanService {

    private final KanbanValidator kanbanValidator;

    private final KanbanBoardRepository kanbanBoardRepository;

    public List<KanbanBoard> getAllBoards() {
        return kanbanBoardRepository.findAll();
    }

    public KanbanBoard findKanbanBoardById(Long id) {
        return kanbanBoardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Не найдена доска с ID %d", id)));
    }

    public List<KanbanBoard> getBoardsByAddedUsername(String username) {
        return kanbanBoardRepository.findKanbanBoardsByUsernameAdded(username);
    }

    public void createBoard(String username, KanbanBoardDetailsRq kanbanBoardDetailsRq) {
        kanbanValidator.validate(kanbanBoardDetailsRq);
        kanbanBoardRepository.save(new KanbanBoard(kanbanBoardDetailsRq.getTitle(), username, new HashSet<>(Arrays.asList(username))));
    }

    @Transactional
    public void addUserToKanbanBoard(String username, BoardToAddRq boardToAddRq) {
        KanbanBoard kanbanBoard = kanbanBoardRepository.findById(boardToAddRq.getBoardId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Не найдена доска с ID %d", boardToAddRq.getBoardId())));
        if (!kanbanBoard.getCreatedBy().equals(username)) {
            throw new ForbiddenException("Только создатель доски может добавлять пользователей");
        }
        kanbanBoard.getUsernameAdded().add(boardToAddRq.getUserToAdd());
        kanbanBoardRepository.save(kanbanBoard);
    }

}



















