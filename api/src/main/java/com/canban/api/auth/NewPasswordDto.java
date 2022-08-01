package com.canban.api.auth;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель данных, необходимых для восстановления пароля")
public class NewPasswordDto {

    @Schema(description = "Никнейм пользователя")
    private String username;
    @Schema(description = "Код для восстановления пароля")
    private String passwordCode;
    @Schema(description = "Новый пароль, который должен ввести пользователь")
    private String newPassword;
    @Schema(description = "Подтверждение нового пароля")
    private String confirmNewPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordCode() {
        return passwordCode;
    }

    public void setPasswordCode(String passwordCode) {
        this.passwordCode = passwordCode;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }
}
