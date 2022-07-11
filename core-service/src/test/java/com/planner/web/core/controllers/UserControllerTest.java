package com.planner.web.core.controllers;

import com.planner.web.core.entities.User;
import com.planner.web.core.mapper.UserMapper;
import com.planner.web.core.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private UserRepository userRepository;

    private List<User> listUsers;
    private User userAlice;
    private User userJohn;

    @BeforeEach
    public void init() {
        userAlice = new User();
        userJohn = new User();
        userAlice.setNickname("Alice");
        userJohn.setNickname("John");
        Mockito.doReturn(Optional.of(userAlice))
                .when(userRepository)
                .findUserByNickname("Alice");

        listUsers = Arrays.asList(userAlice, userJohn);
    }

    @Test
    public void findAllUsersTest() throws Exception {
        given(userRepository.findAll()).willReturn(listUsers);

        mvc.perform(
                get("/api/v1/users")
                        .content(String.valueOf(MediaType.APPLICATION_JSON))
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("[0].nickname", is(listUsers.get(0).getNickname())));
    }

    @Test
    public void findUserByNicknameTest() throws Exception {
        given(userRepository.findUserByNickname(userAlice.getNickname())).willReturn(Optional.ofNullable(userAlice));

        mvc.perform(
                        get("/api/v1/users/Alice")
                                .content(String.valueOf(MediaType.APPLICATION_JSON))
                )
                .andDo(print())
                .andExpect(status().isOk());

    }

}
