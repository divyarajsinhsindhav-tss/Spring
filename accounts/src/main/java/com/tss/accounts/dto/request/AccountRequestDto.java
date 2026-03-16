package com.tss.accounts.dto.request;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AccountRequestDto {

    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name must contain only letters")
    @Size(min = 2, max = 50)
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email formate. e.g. john@doe.com")
    private String email;

    @NotBlank(message = "Phone Number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Enter valid phone number.")
    private String phone;
}
