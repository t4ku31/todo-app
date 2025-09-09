package com.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
// import com.validation.emailValidationSetup.UniqueEmail;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Builder
@Setter
@Getter

public class AuthRequestDto {

    @NotBlank
    @Email
    // @UniqueEmail
    private String email;

    @NotBlank
    @Size(min = 6)
    private String password;

}
