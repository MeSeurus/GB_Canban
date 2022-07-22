package com.canban.auth.mapper;

import com.canban.api.auth.RegistrationUserDto;
import com.canban.auth.entity.Role;
import com.canban.auth.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public User dtoToEntity (RegistrationUserDto registrationUserDto, List<Role> listRole) {
        User user = new User();
        user.setUsername(registrationUserDto.getUsername());
        user.setFirstName(registrationUserDto.getFirstName());
        user.setLastName(registrationUserDto.getLastName());
        user.setPassword(registrationUserDto.getPassword());
        user.setRoles(listRole);
        return user;
    }
}
