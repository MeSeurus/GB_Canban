package com.canban.auth.validator;

import com.canban.api.auth.RegistrationUserDto;
import com.canban.api.exceptions.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.canban.auth.cnst.RegexConst.VALIDATE_EMAIL;
import static com.canban.auth.cnst.RegexConst.VALIDATE_PASSWORD;

@Component
@RequiredArgsConstructor
public class UserValidator {

    public void validate(RegistrationUserDto registrationUserDto) {
        List<String> errors = new ArrayList<>();
        if (registrationUserDto.getUsername().isEmpty()) {
            errors.add("Поле никнейм не должно быть пустым");
        }
        if (registrationUserDto.getUsername().length() < 4) {
            errors.add("Никнейм должен содержать минимум 4 символа");
        }
        if (registrationUserDto.getFirstName().isEmpty()) {
            errors.add("Поле имя не должно быть пустым");
        }
        if (registrationUserDto.getLastName().isEmpty()) {
            errors.add("Поле фамилия не должно быть пустым");
        }
        if (!registrationUserDto.getPassword().matches(VALIDATE_PASSWORD)) {
            errors.add("Пароль должен содержать минимум 8 символов");
        }
        if (registrationUserDto.getPassword().equals(registrationUserDto.getUsername())) {
            errors.add("Пароль не должен совпадать с именем пользователя");
        }
        if (!registrationUserDto.getEmail().matches(VALIDATE_EMAIL)) {
            errors.add("Введите корректный адрес электронной почты");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
