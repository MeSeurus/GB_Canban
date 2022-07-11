package com.planner.web.core.contollers;

import com.planner.web.api.exceptions.ResourceNotFoundException;
import com.planner.web.core.mapper.UserMapper;
import com.planner.web.api.core.UserDto;
import com.planner.web.core.entities.User;
import com.planner.web.core.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "Пользователи", description = "Методы работы с пользователями")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    @Operation(
            summary = "Запрос на создание нового пользователя",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )
    public void createNew(@RequestHeader String nickname) {
        userService.createNew(nickname);
    }

    @GetMapping
    @Operation(
            summary = "Запрос на получение всех пользователей",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = List.class))
                    )
            }
    )
    public List<UserDto> findAll() {
        return userService.findAll().stream().map(u -> userMapper.entityToDto(u)).collect(Collectors.toList());
    }

    @GetMapping("/{nickname}")
    @Operation(
            summary = "Запрос на получение пользователя по имени пользователя",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UserDto.class))
                    )
            }
    )
    public UserDto getByNickname(@PathVariable @Parameter(description = "Имя пользователя", required = true) String nickname) {
        User user = userService.findUserByUsername(nickname).orElseThrow(() -> new ResourceNotFoundException("User not found, nickname = " + nickname));
        return userMapper.entityToDto(user);
    }

    @DeleteMapping("/{nickname}")
    @Operation(
            summary = "Запрос на удаление пользователя по имени пользователя",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )
    public void deleteByNickname(@PathVariable @Parameter(description = "Имя пользователя", required = true) String nickname) {
        userService.deleteByNickname(nickname);
    }


}
