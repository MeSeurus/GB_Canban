package com.canban.web.analytics.repositories;

import com.canban.web.analytics.entities.TasksAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksAnalyticsRepository extends JpaRepository<TasksAnalytics, Long> {
}
