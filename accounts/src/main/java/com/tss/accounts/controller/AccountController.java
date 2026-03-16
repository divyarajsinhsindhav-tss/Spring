package com.tss.accounts.controller;

import com.tss.accounts.dto.request.AccountRequestDto;
import com.tss.accounts.dto.request.AccountUpdateDto;
import com.tss.accounts.dto.request.TransactionRequestDto;
import com.tss.accounts.dto.response.AccountPageDto;
import com.tss.accounts.dto.response.AccountResponseDto;
import com.tss.accounts.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllAccounts(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {

        log.info("Fetching all accounts | pageNumber={} | pageSize={}", pageNumber, pageSize);

        AccountPageDto accountPageDto = accountService.getAllAccounts(pageNumber, pageSize);

        log.info("Successfully fetched accounts");

        return ResponseEntity.status(HttpStatus.OK).body(accountPageDto);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<?> getAccountById(@PathVariable Long accountNumber) {

        log.info("Fetching account | accountNumber={}", accountNumber);

        AccountResponseDto accountResponseDto = accountService.getAccountByAccountNumber(accountNumber);

        log.info("Account fetched successfully | accountNumber={}", accountNumber);

        return ResponseEntity.status(HttpStatus.OK).body(accountResponseDto);
    }

    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<?> deleteAccountByNumber(@PathVariable Long accountNumber) {

        log.info("Deleting account | accountNumber={}", accountNumber);

        accountService.deleteAccountByAccountNumber(accountNumber);

        log.info("Account deleted successfully | accountNumber={}", accountNumber);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/")
    public ResponseEntity<?> saveAccount(@RequestBody AccountRequestDto accountRequestDto) {

        log.info("Creating new account | holderName={}", accountRequestDto.getName());

        AccountResponseDto accountResponseDto = accountService.createAccount(accountRequestDto);

        log.info("Account created successfully | accountNumber={}", accountResponseDto.getAccountNumber());

        return ResponseEntity.status(HttpStatus.CREATED).body(accountResponseDto);
    }

    @PutMapping("/{accountNumber}")
    public ResponseEntity<?> updateAccount(@PathVariable Long accountNumber,
                                           @RequestBody AccountUpdateDto accountUpdateDto) {

        log.info("Updating account | accountNumber={}", accountNumber);

        AccountResponseDto accountResponseDto =
                accountService.updateAccountByAccountNumber(accountNumber, accountUpdateDto);

        log.info("Account updated successfully | accountNumber={}", accountNumber);

        return ResponseEntity.status(HttpStatus.OK).body(accountResponseDto);
    }

    @PostMapping("/credit")
    public ResponseEntity<?> credit(@RequestBody TransactionRequestDto transactionRequestDto) {

        log.info("Credit transaction started | accountNumber={} | amount={}",
                transactionRequestDto.getAccountNumber(),
                transactionRequestDto.getAmount());

        AccountResponseDto accountResponseDto = accountService.credit(transactionRequestDto);

        log.info("Credit successful | accountNumber={} | amount={}",
                transactionRequestDto.getAccountNumber(),
                transactionRequestDto.getAmount());

        return ResponseEntity.status(HttpStatus.OK).body(accountResponseDto);
    }

    @PostMapping("/debit")
    public ResponseEntity<?> debit(@RequestBody TransactionRequestDto transactionRequestDto) {

        log.info("Debit transaction started | accountNumber={} | amount={}",
                transactionRequestDto.getAccountNumber(),
                transactionRequestDto.getAmount());

        AccountResponseDto accountResponseDto = accountService.debit(transactionRequestDto);

        log.info("Debit successful | accountNumber={} | amount={}",
                transactionRequestDto.getAccountNumber(),
                transactionRequestDto.getAmount());

        return ResponseEntity.status(HttpStatus.OK).body(accountResponseDto);
    }

}