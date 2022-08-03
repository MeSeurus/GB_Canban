package com.canban.web.core.services;

import com.canban.api.exceptions.ResourceNotFoundException;
import com.canban.web.core.entities.KanbanBoard;
import com.canban.web.core.mapper.KanbanBoardMapper;
import com.canban.web.core.repositories.KanbanBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class KanbanService {

    private final KanbanBoardRepository kanbanBoardRepository;
    private final KanbanBoardMapper kanbanBoardMapper;

    @Transactional
    public void createBoard(String username, KanbanBoard kanbanBoard) {
        kanbanBoard.setCreatedBy(username);
        kanbanBoard.setUsers(Set.of(username));
        kanbanBoardRepository.save(kanbanBoard);
    }

    public List<KanbanBoard> getBoards(String username) {
        return kanbanBoardRepository.findAllByUser(username);
    }

    public KanbanBoard getBoard(Long id){
        return kanbanBoardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Не найдена доска с ID %d", id)));
    }

    @Transactional
    public void addUserToKanbanBoard(Long id, String usernameToAdd) {
        KanbanBoard kanbanBoard = getBoard(id);
        Set<String> users = kanbanBoard.getUsers();
        users.add(usernameToAdd);
        kanbanBoard.setUsers(users);
        kanbanBoardRepository.save(kanbanBoard);
    }
}
