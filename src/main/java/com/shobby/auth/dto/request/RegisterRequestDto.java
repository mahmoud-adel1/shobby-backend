package com.shobby.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RegisterRequestDto {
    @NotBlank(message = "Username must not be empty")
    private String username;

    @NotBlank(message = "Email must not be empty")
    @Email(message = "Email is not valid")
    private String email;

    @NotBlank(message = "Password must not be empty")
    @Min(value = 8, message = "Password must be at least 8 characters")
    private String password;

    @NotBlank(message = "Mobile number must not be empty")
    @Pattern(regexp = "^\\d{11}$")
    private String mobileNumber;
}
