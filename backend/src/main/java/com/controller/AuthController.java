package com.controller;

import com.service.AuthService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.dto.SignupRequestDto;
import com.dto.SigninRequestDto;
import jakarta.validation.Valid;
import java.util.logging.Logger;

@RestController
@RequestMapping("/auth")
class AuthController {
    private final AuthService authService;

    private static final Logger logger = Logger.getLogger(AuthController.class.getName());

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestBody @Valid SigninRequestDto request) {

        String username = request.getEmail();
        String password = request.getPassword();
        logger.info("password from Authcontroller: " + password);
        logger.info("email from Authcontroller: " + username);
        String token = authService.signin(username, password);
        logger.info("returned token" + token);
        if (token != null) {
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid SignupRequestDto request) {
        String email = request.getEmail();
        String password = request.getPassword();

        logger.fine("authcontroller signup - email: " + email + ", password: " + password);
        String token = authService.signup(email, password);
        if (token != null) {
            return ResponseEntity.ok("User registered successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User registration failed");
        }
    }
}