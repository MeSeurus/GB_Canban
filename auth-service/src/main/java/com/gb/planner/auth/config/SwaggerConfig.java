package com.gb.planner.auth.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI authService() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Authentication Service for GB Planner application")
                                .version("1.0.0")
                                .contact(
                                        new Contact()
                                                .email("novitskynick@gmail.com")
                                                .url("https://gb.ru")
                                                .name("Nikolay Novitsky")
                                )
                );
    }
}
