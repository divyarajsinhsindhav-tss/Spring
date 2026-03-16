package com.tss.accounts.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AccountUpdateDto {

    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Enter valid phone number.")
    private String phone ;

    @Email(message = "Invalid email formate. e.g. john@doe.com")
    private String email;
}
