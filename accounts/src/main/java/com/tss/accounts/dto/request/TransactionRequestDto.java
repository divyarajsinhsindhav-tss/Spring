package com.tss.accounts.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TransactionRequestDto {

    @NotNull(message = "Account number must needed")
    private Long accountNumber;

    @NotNull(message = "Amount must needed")
    @Positive(message = "Amount is positive number")
    private Double amount;
}
