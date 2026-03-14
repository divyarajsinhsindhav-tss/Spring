package com.tss.accounts.dto.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AccountUpdateDto {
    private String phone ;
    private String email;
}
