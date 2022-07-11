package com.planner.web.core.services;

import com.planner.web.core.entities.Day;
import com.planner.web.core.repositories.DayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DayService {

    private final DayRepository dayRepository;

    public List<Day> findAll() {
        return dayRepository.findAll();
    }

    public Optional<Day> findById(Long id) {
        return dayRepository.findById(id);
    }

    public void createNew(Day day) {
        dayRepository.save(day);
    }

    public void deleteById(Long id) {
        dayRepository.deleteById(id);
    }

    @PostConstruct
    private void init(){
        fillCalendar(LocalDate.now().getYear(), LocalDate.now().getYear() + 1);

    }

    private void fillCalendar(int startYear, int endYear) {

        List<LocalDate> targetDatesList = new ArrayList<>();

        LocalDate startDate = LocalDate.of(startYear, 1, 1);
        LocalDate endDate = LocalDate.of(endYear, 12, 31);
        for(LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)){
            targetDatesList.add(date);
        }
        List<LocalDate> dayList = dayRepository.findByDateBetween(LocalDate.of(startYear, 1, 1),
                        LocalDate.of(endYear, 12, 31))
                .stream()
                .map(Day::getDate).
                collect(Collectors.toList());

        targetDatesList.removeAll(dayList);
        List<Day> targetDaysList = targetDatesList.stream()
                .map(Day::new)
                .collect(Collectors.toList());

        dayRepository.saveAll(targetDaysList);

    }
}
