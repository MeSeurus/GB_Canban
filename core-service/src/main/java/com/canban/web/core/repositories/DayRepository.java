package com.canban.web.core.repositories;

import com.canban.web.core.entities.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DayRepository extends JpaRepository<Day, Long> {

    List<Day> findByDateBetween(LocalDate start, LocalDate end);
}
