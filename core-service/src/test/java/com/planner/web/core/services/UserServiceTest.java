package com.planner.web.core.services;

import com.planner.web.core.entities.User;
import com.planner.web.core.repositories.UserRepository;
import com.planner.web.core.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        User userAlice = new User();
        User userJohn = new User();
        userAlice.setNickname("Alice");
        userJohn.setNickname("John");
        Mockito.doReturn(Optional.of(userAlice))
                .when(userRepository)
                .findUserByNickname("Alice");

        List<User> listUsers = Arrays.asList(userAlice, userJohn);

        Mockito.doReturn(listUsers)
                .when(userRepository)
                .findAll();
    }

    @Test
    public void findOneUserTest() {
        User userAlice = userService.findUserByUsername("Alice").get();
        Assertions.assertNotNull(userAlice);
        Mockito.verify(userRepository, Mockito.times(1)).findUserByNickname("Alice");
    }

    @Test
    public void findAllUserTest() {
        List<User> users = userService.findAll();
        Assertions.assertNotNull(users);
        Assertions.assertEquals(2, users.size());
        Mockito.verify(userRepository, Mockito.times(1)).findAll();
    }



}
