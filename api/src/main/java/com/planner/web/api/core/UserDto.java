package com.planner.web.api.core;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель пользователя")
public class UserDto {
    @Schema(description = "ID пользователя", required = true, example = "1")
    private Long id;

    @Schema(description = "Имя пользователя", required = true, example = "user1")
    private String nickname;

    public UserDto(String nickname) {
        this.nickname = nickname;
    }

    public UserDto(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
