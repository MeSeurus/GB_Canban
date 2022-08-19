package com.canban.auth.controller;

import com.canban.api.auth.NewPasswordDto;
import com.canban.auth.exceptions.InvalidRegistrationException;
import com.canban.auth.service.UserAccessManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
    public void activation(@RequestParam(name = "username") @Parameter(description = "Имя пользователя, запросившего ссылку на активацию")  String username,
                           @RequestParam(name = "code") @Parameter(description = "Код, необходимый для активации аккаунта пользователя") String code) {
        userAccessManagementService.checkActivateKey(username, code);
    }

    @PostMapping("/password/recovery")
    @Operation(
            summary = "Запрос ссылки для восстановления пароля",
            description = "Позволяет пользователю выслать на email, указанный при регистрации, ссылку для восстановления пароля",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "200")
            })
    public void passwordRecovery(@RequestBody @Parameter(description = "Имя пользователя, запросившего ссылку на смену пароля") String username) {
        userAccessManagementService.sendRecoverPasswordLink(username);
    }

    @PostMapping("activation/recovery")
    @Operation(
            summary = "Отправка на почту пользователя новой ссылки для активации аккаунта",
            description = "Позволяет пользователю, потерявшему ссылку на активацию, выслать себе новую",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "200")
            }
    )
    public void activationLinkRecovery(@RequestBody @Parameter(description = "Имя пользователя, повторно запросившего ссылку на активацию") String username) {
        userAccessManagementService.sendNewActivationLink(username);
    }

    @PostMapping("/password/set")
    @Operation(
            summary = "Установка нового пароля",
            description = "Позволяет пользователю установить себе новый пароль",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "200")
            }
    )
    public void setNewPassword(@RequestBody @Parameter(description = "Модель данных, необходимых для восстановления пароля") NewPasswordDto newPasswordDto) {
        if (!newPasswordDto.getNewPassword().equals(newPasswordDto.getConfirmNewPassword())) {throw new InvalidRegistrationException("Пароли не совпадают");}
        userAccessManagementService.setNewPassword(newPasswordDto.getUsername(), passwordEncoder.encode(newPasswordDto.getNewPassword()), newPasswordDto.getPasswordCode());
    }

}
