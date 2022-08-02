package com.canban.web.analytics.repositories;

import com.canban.web.analytics.entities.EventsAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsAnalyticsRepository extends JpaRepository<EventsAnalytics, Long> {
}
