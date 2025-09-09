package com.controller;

import com.config.AppConfigurationProperties;
import com.service.AuthService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import com.dto.AuthRequestDto;
import com.dto.AuthResponse;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.logging.Logger;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AppConfigurationProperties properties;
    private final AuthService authService;

    private static final Logger logger = Logger.getLogger(AuthController.class.getName());

    public AuthController(AuthService authService, AppConfigurationProperties properties) {
        this.authService = authService;
        this.properties = properties;
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody @Valid AuthRequestDto request,
            HttpServletResponse response) {

        String email = request.getEmail();
        String password = request.getPassword();
        logger.fine("password from Authcontroller: " + password);
        logger.fine("email from Authcontroller: " + email);
        AuthResponse auth = authService.signin(email, password);

        if (auth != null) {
            ResponseCookie cookie = ResponseCookie.from(properties.getCookie().getName(), auth.getToken())
                    .httpOnly(true)
                    .secure(true)
                    .path("/")
                    .maxAge(properties.getCookie().getExpiresIn())
                    .sameSite("None")
                    .build();

            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
            return ResponseEntity.ok(auth);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody @Valid AuthRequestDto request,
            HttpServletResponse response) {
        String email = request.getEmail();
        String password = request.getPassword();

        logger.fine("authcontroller signup - email: " + email + ", password: " + password);
        AuthResponse auth = authService.signup(email, password);
        if (auth != null) {
            ResponseCookie cookie = ResponseCookie.from(properties.getCookie().getName(), auth.getToken())
                    .httpOnly(true)
                    .secure(true)
                    .path("/")
                    .maxAge(properties.getCookie().getExpiresIn())
                    .sameSite("None")
                    .build();

            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
            return ResponseEntity.ok(auth);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}