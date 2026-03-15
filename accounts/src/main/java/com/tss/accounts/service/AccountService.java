package com.tss.accounts.service;

import com.tss.accounts.dto.request.AccountRequestDto;
import com.tss.accounts.dto.request.AccountUpdateDto;
import com.tss.accounts.dto.request.TransactionRequestDto;
import com.tss.accounts.dto.response.AccountPageDto;
import com.tss.accounts.dto.response.AccountResponseDto;

public interface AccountService {

    AccountPageDto getAllAccounts(Integer pageNumber, Integer pageSize);

    AccountResponseDto getAccountByAccountNumber(Long accountNumber);

    AccountResponseDto createAccount(AccountRequestDto accountRequestDto);

    void deleteAccountByAccountNumber(Long accountNumber);

    AccountResponseDto updateAccountByAccountNumber(Long accountNumber, AccountUpdateDto dto);

    AccountResponseDto debit(TransactionRequestDto transactionRequestDto);

    AccountResponseDto credit(TransactionRequestDto transactionRequestDto);
}
