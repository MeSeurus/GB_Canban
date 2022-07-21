package com.canban.web.core.repositories;

import com.canban.web.core.entities.Event;
import com.canban.web.core.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(
            value = "SELECT e from Task e where e.username = :username"
    )
    List<Task> findTasksByUserName(String username);
}