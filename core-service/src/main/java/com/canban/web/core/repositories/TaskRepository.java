package com.canban.web.core.repositories;

import com.canban.web.core.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Event, Long> {

    List<Event> findTasksByUserName(String username);

    @Query(
            value = "SELECT e from Event e where e.userName = :username"
    )
    List<Event> findEventsByUserNickname(String username);

}
