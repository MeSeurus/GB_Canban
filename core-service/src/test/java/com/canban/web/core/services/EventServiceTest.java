package com.canban.web.core.services;


import com.canban.web.core.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class EventServiceTest {

    /*@Autowired
    private EventService eventService;

    @MockBean
    private EventRepository eventRepository;

    private User userJohn;
/*
    @BeforeEach
    public void init() {
        userJohn = new User("John");
        Event createProgram =
                Event.builder()
                        .id(1L)
                        .title("Create program")
                        .content("test")
                        .user(userJohn)
                        .build();
        Event cookDinner = Event.builder().build();
        Event washCar = Event.builder().user(userJohn).build();

        List<Event> events = Arrays.asList(createProgram, cookDinner, washCar);
        Mockito.doReturn(events)
                .when(eventRepository)
                .findAll();

        List<Event> johnEvents = Arrays.asList(washCar, createProgram);
        Mockito.doReturn(johnEvents)
                .when(eventRepository)
                .findEventsByUser(userJohn.getNickname());

        //       List<Event> dayEvents = Arrays.asList(createProgram, cookDinner);
        //       Mockito.doReturn(dayEvents)
//                .when(eventRepository)
//                .findAllByDayId(day.getId());
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
        Assertions.assertNotNull(dayIdEvents);
        Assertions.assertEquals(2, dayIdEvents.size());
        Mockito.verify(eventRepository, Mockito.times(1)).findAllByDayId(day.getId());
    }

*/
}
