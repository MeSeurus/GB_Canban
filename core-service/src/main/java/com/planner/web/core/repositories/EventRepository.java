package com.planner.web.core.repositories;

import com.planner.web.core.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(
            value = "SELECT e from Event e where e.day.id = :id"
    )
    List<Event> findAllByDayId(@Param("id") Long id);

    List<Event> findEventsByUser(String username);

}
