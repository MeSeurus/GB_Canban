package com.canban.web.analytics.repositories;

import com.canban.web.analytics.entities.EventsAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EventsAnalyticsRepository extends JpaRepository<EventsAnalytics, Long> {

    @Query(
            value = "select count(*) from events_analytics " +
                    "where (CURRENT_DATE -  event_begin_date) < :timeForSearch " +
                    "and event_username = :username ",
            nativeQuery = true
    )
    Integer getCountOfEvents(String username, LocalDateTime timeForSearch);

    @Query(
            value = "select * from events_analytics " +
                    "where event_begin_date > :timeForSearch " +
                    "and event_username = :username " +
                    "order by (event_end_date - event_begin_date) desc limit 1",
            nativeQuery = true
    )
    Optional<EventsAnalytics> searchLongest(String username, LocalDateTime timeForSearch);

    @Query(
            value = "select * from events_analytics " +
                    "where event_begin_date > :timeForSearch  " +
                    "and event_username = :username " +
                    "order by (event_end_date - event_begin_date) asc limit 1",
            nativeQuery = true
    )
    Optional<EventsAnalytics> searchShortest(String username, LocalDateTime timeForSearch);

}
