package com.canban.web.core.mapper;

import com.canban.api.core.UserDto;
import com.canban.web.core.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User dtoToEntity(UserDto userDto) {
        return new User(userDto.getNickname());
    }

    public UserDto entityToDto(User user) {
        return new UserDto(user.getId(), user.getNickname());
    }


}
