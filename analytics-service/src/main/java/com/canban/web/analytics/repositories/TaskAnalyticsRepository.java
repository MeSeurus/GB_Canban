package com.canban.web.analytics.repositories;

import com.canban.web.analytics.entities.TaskAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface TaskAnalyticsRepository extends JpaRepository<TaskAnalytics, Long> {

    @Query(
            value = "select count(DISTINCT task_id) from tasks_analytics " +
                    "where task_begin_date > :timeForSearch " +
                    "and task_actual_end_date < CURRENT_DATE " +
                    "and task_user_executor = :username",
            nativeQuery = true
    )
    Integer getCountOfCompletedTasks(String username, LocalDateTime timeForSearch);

    @Query(
            value = "select * from tasks_analytics " +
                    "where task_begin_date > :timeForSearch " +
                    "and task_actual_end_date < CURRENT_DATE " +
                    "and task_user_executor = :username " +
                    "order by :orderType * (task_actual_end_date - task_begin_date) limit 1",
            nativeQuery = true
    )
    Optional<TaskAnalytics> searchTheLongestAndTheShortestCompletedTaskByUsernameAndByTimePeriod(String username, LocalDateTime timeForSearch, Integer orderType);


}
