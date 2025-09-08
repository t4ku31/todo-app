package com.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
// import com.validation.emailValidationSetup.UniqueEmail;
import jakarta.validation.constraints.Size;

public class SignupRequestDto {

    @NotBlank
    @Email
    // @UniqueEmail
    private String email;

    @NotBlank
    @Size(min = 6)
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
