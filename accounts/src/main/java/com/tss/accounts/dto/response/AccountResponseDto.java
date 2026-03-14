package com.tss.accounts.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AccountResponseDto {
    private Long accountNumber;
    private String name;
    private Double balance;
    private String email;
    private String phone;
}
