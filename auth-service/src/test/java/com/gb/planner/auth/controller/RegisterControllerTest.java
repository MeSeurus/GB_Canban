package com.gb.planner.auth.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.gb.planner.FlywayMigrationConfig;
import com.gb.planner.api.RegistrationUserDto;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Import(FlywayMigrationConfig.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@Order(1)
public class RegisterControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }


    static RegistrationUserDto buildRegistrationUserDto() {
        RegistrationUserDto registrationUserDto = new RegistrationUserDto();
        registrationUserDto.setId(199L);
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
