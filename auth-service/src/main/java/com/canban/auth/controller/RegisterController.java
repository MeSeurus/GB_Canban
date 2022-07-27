package com.canban.auth.controller;

import com.canban.api.auth.RegistrationUserDto;
import com.canban.auth.exceptions.InvalidRegistrationException;
import com.canban.auth.mapper.UserMapper;
import com.canban.auth.service.RoleService;
import com.canban.auth.service.UserAccessManagementService;
import com.canban.auth.service.UserService;
import com.canban.auth.validator.UserValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
@Tag(name = "Контроллер, отвечающий за регистрацию пользователей", description = "Позволяет зарегистрироваться новому пользователю")
public class RegisterController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserValidator userValidator;
    private final UserMapper userMapper;
    private final RoleService roleService;
    private final UserAccessManagementService userAccessManagementService;

    @PostMapping("/registration")
    @Operation(
            summary = "Регистрация пользователя",
            description = "Позволяет зарегистрировать нового пользователя",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "200")
            }
    )
    public ResponseEntity<?> registerNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
            throw new InvalidRegistrationException("Пароли не совпадают");
        }
        if (userService.findByUsername(registrationUserDto.getUsername()).isPresent()) {
            throw new InvalidRegistrationException("Пользователь с таким именем уже существует");
        }
        userValidator.validate(registrationUserDto);
        registrationUserDto.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
        userService.createUser(userMapper.dtoToEntity(registrationUserDto,List.of(roleService.getUserRole())));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/activation")
    @Operation(
            summary = "Активация пользователя",
            description = "Позволяет активировать нового пользователя",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "200")
            }
    )
    public ResponseEntity<?> activation(@RequestParam (name = "username") String username, @RequestParam (name = "code") String code){
        userAccessManagementService.checkActivateKey(username, code);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
