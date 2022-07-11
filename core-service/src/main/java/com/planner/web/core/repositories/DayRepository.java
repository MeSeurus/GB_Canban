package com.planner.web.core.repositories;

import com.planner.web.core.entities.Day;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DayRepository extends JpaRepository<Day, Long> {

    List<Day> findByDateBetween(LocalDate start, LocalDate end);
}
