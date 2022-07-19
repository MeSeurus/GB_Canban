package com.canban.auth.validator;

import com.canban.api.auth.RegistrationUserDto;
import com.canban.api.exceptions.ValidationException;
import com.canban.api.exceptions.FieldsValidationError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class UserValidator {

    public static boolean patternMatches(String emailAddress) {
        String regexPattern ="^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'* +/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

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
        if (registrationUserDto.getPassword().length() < 8) {
            errors.add("Пароль должен содержать минимум 8 символов");
        }
        if (registrationUserDto.getPassword().equals(registrationUserDto.getUsername())) {
            errors.add("Пароль не должен совпадать с именем пользователя");
        }
        if (!patternMatches(registrationUserDto.getEmail())) {
            errors.add("Введите корректный адрес электронной почты");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
