package com.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import com.model.User;
import com.repository.UserRepository;
import java.util.logging.Logger;

@Validated
@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private static final Logger logger = Logger.getLogger(AuthService.class.getName());

    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public String signin(String email, String password) {
        logger.info("input check - email: " + email + ", password: " + password);
        User user = userRepository.findByEmail(email);

        logger.info("sign in check - user email: " + user.getEmail());

        if (user == null) {
            return null;
        }

        logger.info("sign in check - user email: " + user.getEmail());

        if (passwordEncoder.matches(password, user.getPassword())) {
            logger.info("clear password");
            return "dummy-token";
        }
        return null;
    }

    public String signup(String email, String password) {
        logger.info("Signup started for email: " + email);
        String encodedPassword = passwordEncoder.encode(password);

        logger.fine("Raw password: " + password);
        User user = new User();
        user.setEmail(email);
        user.setPassword(encodedPassword);
        User savedUser = userRepository.save(user);

        return savedUser.getId().toString();
    }
}