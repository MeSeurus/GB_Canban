package com.canban.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.canban.FlywayCleanBaseForTestsConfig;
import com.canban.api.auth.RegistrationUserDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Import(FlywayCleanBaseForTestsConfig.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class RegisterControllerTest {

    @Autowired
    private MockMvc mvc;

    static RegistrationUserDto buildRegistrationUserDto() {
        RegistrationUserDto registrationUserDto = new RegistrationUserDto();
        registrationUserDto.setUsername("testuser11");
        registrationUserDto.setPassword("testpass2");
        registrationUserDto.setConfirmPassword("testpass2");
        registrationUserDto.setFirstName("Bob7");
        registrationUserDto.setLastName("Dilan3");
        registrationUserDto.setEmail("bobdilan11@yandex.ru");
        return registrationUserDto;
    }

    static ObjectMapper mapper = JsonMapper.builder()
            .findAndAddModules()
            .build();

    @Test
    public void registrationTest() throws Exception {
        mvc
                .perform(post("/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(buildRegistrationUserDto()))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(mvcResult -> {
                    String json = mvcResult.getResponse().getContentAsString();
                    assertTrue(json.contains("\"token\":"));
                });
    }
}
