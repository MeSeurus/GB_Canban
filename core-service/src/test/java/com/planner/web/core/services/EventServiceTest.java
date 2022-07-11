package com.planner.web.core.services;

import com.planner.web.core.entities.Day;
import com.planner.web.core.entities.Event;
import com.planner.web.core.entities.User;
import com.planner.web.core.repositories.EventRepository;
import com.planner.web.core.services.EventService;
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

@SpringBootTest
public class EventServiceTest {

    @Autowired
    private EventService eventService;

    @MockBean
    private EventRepository eventRepository;

    private User userJohn;
    private Day day;

    @BeforeEach
    public void init() {
        userJohn = new User("John");
        day = new Day(1l, LocalDate.of(2022, 1, 1));
        Event createProgram =
                Event.builder()
                        .id(1l)
                        .title("Create program")
                        .content("test")
                        .day(day)
                        .user(userJohn)
                        .build();
        Event cookDinner = Event.builder().day(day).build();
        Event washCar = Event.builder().user(userJohn).build();

        List<Event> events = Arrays.asList(createProgram, cookDinner, washCar);
        Mockito.doReturn(events)
                .when(eventRepository)
                .findAll();

        List<Event> johnEvents = Arrays.asList(washCar, createProgram);
        Mockito.doReturn(johnEvents)
                .when(eventRepository)
                .findEventsByUser(userJohn.getNickname());

        List<Event> dayEvents = Arrays.asList(createProgram, cookDinner);
        Mockito.doReturn(dayEvents)
                .when(eventRepository)
                .findAllByDayId(day.getId());
    }

    @Test
    public void findEventsByUsernameTest() {
        List<Event> johnEvents = eventRepository.findEventsByUser(userJohn.getNickname());
        Assertions.assertNotNull(johnEvents);
        Assertions.assertEquals(2, johnEvents.size());
        Mockito.verify(eventRepository, Mockito.times(1)).findEventsByUser(userJohn.getNickname());
    }

    @Test
    public void findAllEventsTest() {
        List<Event> events = eventService.findAll();
        Assertions.assertNotNull(events);
        Assertions.assertEquals(3, events.size());
        Mockito.verify(eventRepository, Mockito.times(1)).findAll();
    }


    @Test
    public void findByDayIdTest() {
        List<Event> dayIdEvents = eventService.findEventsByDayId(1l);
        Assertions.assertNotNull(dayIdEvents);
        Assertions.assertEquals(2, dayIdEvents.size());
        Mockito.verify(eventRepository, Mockito.times(1)).findAllByDayId(day.getId());
    }


}
