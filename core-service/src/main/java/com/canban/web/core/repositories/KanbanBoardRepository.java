package com.canban.web.core.repositories;

import com.canban.web.core.entities.KanbanBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KanbanBoardRepository extends JpaRepository<KanbanBoard, Long> {

//    @Query(value = "select k from KanbanBoard k join fetch k.usernameAdded where k.usernameAdded =:username") -- это вызывает ошибку, пока осталвю таку
    List<KanbanBoard> findKanbanBoardsByUsernameAdded(String username);
}