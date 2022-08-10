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
            errors.add("User name must be filled in.");
        }
        if (registrationUserDto.getUsername().length() < 4) {
            errors.add("The nickname must contain at least 4 characters");
        }
        if (registrationUserDto.getFirstName().isEmpty()) {
            errors.add("Name must be filled in.");
        }
        if (registrationUserDto.getLastName().isEmpty()) {
            errors.add("Last name must be filled in.");
        }
        if (!registrationUserDto.getPassword().matches(VALIDATE_PASSWORD)) {
            errors.add("The password must contain at least 8 characters");
        }
        if (registrationUserDto.getPassword().equals(registrationUserDto.getUsername())) {
            errors.add("The password must not match the user name");
        }
        if (!registrationUserDto.getEmail().matches(VALIDATE_EMAIL)) {
            errors.add("Enter the correct email address");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
