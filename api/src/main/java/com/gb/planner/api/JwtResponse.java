package com.gb.planner.api;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ответ на JWT-запрос")
public class JwtResponse {
    @Schema(description = "JWT-токен")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JwtResponse(String token) {
        this.token = token;
    }

    public JwtResponse() {
    }
}
