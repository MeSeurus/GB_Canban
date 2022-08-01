package com.canban.auth.controller;

import com.canban.api.auth.NewPasswordDto;
import com.canban.auth.exceptions.InvalidRegistrationException;
import com.canban.auth.service.UserAccessManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/user/access/management")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
@Tag(name = "Контроллер, отвечающий за управлением доступом пользователей", description = "Позволяет восстановить пароль, активировать пользователя ")
public class UserAccessManagementController {

    private final UserAccessManagementService userAccessManagementService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/activation")
    @Operation(
            summary = "Активация пользователя",
            description = "Позволяет активировать нового пользователя",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "200")
            }
    )
    public ResponseEntity<?> activation(@RequestParam(name = "username") String username, @RequestParam (name = "code") String code){
        userAccessManagementService.checkActivateKey(username, code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/recovery/password")
    @Operation(
            summary = "Запрос ссылки для восстановления пароля",
            description = "Позволяет пользователю выслать на email, указанный при регистрации, ссылку для восстановления пароля",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "200")
            })
    public ResponseEntity<?> recoverPassword (@RequestBody String username){
        userAccessManagementService.sendRecoverPasswordLink(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/set/password")
    @Operation(
            summary = "Установка нового пароля",
            description = "Позволяет пользователю установить себе новый пароль",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "200")
            }
    )
    public ResponseEntity<?> setNewPassword(@RequestBody NewPasswordDto newPasswordDto){
        if (!newPasswordDto.getNewPassword().equals(newPasswordDto.getConfirmNewPassword())){
            throw new InvalidRegistrationException("Пароли не совпадают");
        }
        if (newPasswordDto.getNewPassword().length() < 8){
            throw new InvalidRegistrationException("Пароль не должен быть меньше 8 символов");
        }
        userAccessManagementService.setNewPassword(newPasswordDto.getUsername(),passwordEncoder.encode(newPasswordDto.getNewPassword()),newPasswordDto.getPasswordCode());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
