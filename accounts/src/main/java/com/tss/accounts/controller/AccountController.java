package com.tss.accounts.controller;

import com.tss.accounts.dto.request.AccountRequestDto;
import com.tss.accounts.dto.request.AccountUpdateDto;
import com.tss.accounts.dto.response.AccountPageDto;
import com.tss.accounts.dto.response.AccountResponseDto;
import com.tss.accounts.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllAccounts(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        try {
            AccountPageDto accountPageDto = accountService.getAllAccounts(pageNumber, pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(accountPageDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<?> getAccountById(@PathVariable Long accountNumber) {
        try {
            AccountResponseDto accountResponseDto = accountService.getAccountByAccountNumber(accountNumber);
            return ResponseEntity.status(HttpStatus.OK).body(accountResponseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<?> deleteAccountByNumber(@PathVariable Long accountNumber) {
        try {
            accountService.deleteAccountByAccountNumber(accountNumber);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> saveAccount(@RequestBody AccountRequestDto accountRequestDto) {
        try {
            AccountResponseDto accountResponseDto = accountService.createAccount(accountRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(accountResponseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{accountNumber}")
    public ResponseEntity<?> updateAccount(@PathVariable Long accountNumber, @RequestBody AccountUpdateDto accountUpdateDto) {
        try {
            AccountResponseDto accountResponseDto = accountService.updateAccountByAccountNumber(accountNumber, accountUpdateDto);
            return ResponseEntity.status(HttpStatus.OK).body(accountResponseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
