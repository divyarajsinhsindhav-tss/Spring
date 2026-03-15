package com.tss.accounts.dto.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TransactionRequestDto {
    private Long accountNumber;
    private Double amount;
}
