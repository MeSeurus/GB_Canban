package com.canban.web.analytics.repositories;

import com.canban.web.analytics.entities.EventsAnalytics;
import com.canban.web.analytics.entities.TasksAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface TasksAnalyticsRepository extends JpaRepository<TasksAnalytics, Long> {

    @Query(
            value = "select count(*) from tasks_analytics " +
                    "where (CURRENT_DATE -  task_begin_date) < :timeForSearch " +
                    "and :executorOrCreator = :username ",
            nativeQuery = true
    )
    Integer getCountOfEvents(String username, String executorOrCreator, LocalDateTime timeForSearch);

    @Query(
            value = "select * from tasks_analytics " +
                    "where task_begin_date > :timeForSearch " +
                    "and event_username = :username " +
                    "order by (task_end_date - task_begin_date) desc limit 1",
            nativeQuery = true
    )
    Optional<EventsAnalytics> searchLongest(String username, LocalDateTime timeForSearch);

    @Query(
            value = "select * from tasks_analytics " +
                    "where event_begin_date > :timeForSearch " +
                    "and event_username = :username " +
                    "order by (event_end_date - event_begin_date) asc limit 1",
            nativeQuery = true
    )
    Optional<EventsAnalytics> searchShortest(String username, LocalDateTime timeForSearch);


}
