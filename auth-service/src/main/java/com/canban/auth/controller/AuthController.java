package com.canban.auth.controller;

import com.canban.api.auth.JwtRequest;
import com.canban.api.auth.JwtResponse;
import com.canban.auth.entity.User;
import com.canban.auth.exceptions.InvalidAuthorizationException;
import com.canban.auth.service.UserService;
import com.canban.auth.util.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
@Tag(name = "Контроллер, отвечающий за аутентификацию пользователей", description = "Позволяет получить уникальный токен на основании запроса аутентификации")
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    private List<User> users = new CopyOnWriteArrayList<>();

    @PostMapping("/auth")
    @Operation(
            summary = "Аутентификация",
            description = "Позволяет аутентифицировать пользователя и возвращает токен",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "200")
            }
    )
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            if (passwordGuessingProtection(authRequest.getUsername())) {
                log.warn("Пользователь " + authRequest.getUsername() + " пытается подобрать пароль");
            }
            throw new InvalidAuthorizationException("Incorrect username or password");
        }

        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    /**
     * Проверка на попытку подбора пароля к аккаунту
     */
    private boolean passwordGuessingProtection(String username) {
        User user = null;
        boolean isFinded = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                user = users.get(i);
                isFinded = true;
                break;
            }
        }
        if (user == null)  {
            user = new User(username);
        }
        user.setMistakeNum(user.getMistakeNum() + 1);
        if (!isFinded) {
            users.add(user);
        }
        if (user.getMistakeNum() >= 3) {
            user.setMistakeNum(0);
            return true;
        }
        return false;
    }


}
