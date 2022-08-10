package com.canban.web.analytics.repositories;

import com.canban.web.analytics.entities.EventsAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EventsAnalyticsRepository extends JpaRepository<EventsAnalytics, Long> {

    @Query(
            value = "select count(DISTINCT event_id) from events_analytics " +
                    "where event_begin_date > :timeForSearch " +
                    "and event_end_date < CURRENT_DATE " +
                    "and event_username = :username",
            nativeQuery = true
    )
    Integer getCountOfCompletedEvents(String username, LocalDateTime timeForSearch);

    @Query(
            value = "select * from events_analytics " +
                    "where event_begin_date > :timeForSearch " +
                    "and event_end_date < CURRENT_DATE " +
                    "and event_username = :username " +
                    "order by :orderType * (event_end_date - event_begin_date) limit 1",
            nativeQuery = true
    )
    Optional<EventsAnalytics> searchTheLongestAndTheShortestCompletedEventByUsernameAndByTimePeriod(String username, LocalDateTime timeForSearch, Integer orderType);

}
