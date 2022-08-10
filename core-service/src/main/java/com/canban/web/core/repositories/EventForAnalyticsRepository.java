package com.canban.web.core.repositories;

import com.canban.web.core.entities.EventForAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventForAnalyticsRepository extends JpaRepository<EventForAnalytics, Long> {
}
