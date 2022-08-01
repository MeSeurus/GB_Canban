package com.canban.web.core.repositories;

import com.canban.web.core.entities.Event;
import com.canban.web.core.entities.KanbanBoard;
import com.canban.web.core.mapper.KanbanBoardMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KanbanBoardRepository extends JpaRepository<KanbanBoard, Long> {

    public List<KanbanBoard> findAllByCreator(String creator);

}
