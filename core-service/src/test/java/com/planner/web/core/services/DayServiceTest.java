package com.planner.web.core.services;

import com.planner.web.core.entities.Day;
import com.planner.web.core.repositories.DayRepository;
import com.planner.web.core.services.DayService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class DayServiceTest {

    @Autowired
    private DayService dayService;

    @MockBean
    private DayRepository dayRepository;

    @BeforeEach
    public void init() {
        Day day1 = new Day(1l, LocalDate.of(2022, 1, 1));
        Day day2 = new Day(2l, LocalDate.of(2022, 1, 2));
        Day day3 = new Day(3l, LocalDate.of(2022, 1, 3));
        List<Day> days = Arrays.asList(day1, day2, day3);

        Mockito.doReturn(days)
                .when(dayRepository)
                .findAll();

        Mockito.doReturn(Optional.of(day2))
                .when(dayRepository)
                .findById(day2.getId());
    }

    @Test
    public void findDayByIdTest() {
        Day day2 = dayService.findById(2l).get();
        Assertions.assertNotNull(day2);
        Mockito.verify(dayRepository, Mockito.times(1)).findById(2l);
    }

    @Test
    public void findAllDaysTest() {
        List<Day> days = dayService.findAll();
        Assertions.assertNotNull(days);
        Assertions.assertEquals(3, days.size());
        Mockito.verify(dayRepository, Mockito.times(1)).findAll();
    }


}
