package com.tss.accounts.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AccountRequestDto {
    private String name;
    private String email;
    private String phone;
}
