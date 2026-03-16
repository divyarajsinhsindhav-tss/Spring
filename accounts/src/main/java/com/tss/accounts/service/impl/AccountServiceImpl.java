package com.tss.accounts.service.impl;

import com.tss.accounts.dto.request.AccountRequestDto;
import com.tss.accounts.dto.request.AccountUpdateDto;
import com.tss.accounts.dto.request.TransactionRequestDto;
import com.tss.accounts.dto.response.AccountPageDto;
import com.tss.accounts.dto.response.AccountResponseDto;
import com.tss.accounts.entity.Account;
import com.tss.accounts.exception.ApplicationException;
import com.tss.accounts.mapper.AccountMapper;
import com.tss.accounts.repository.AccountRepository;
import com.tss.accounts.service.AccountService;
import com.tss.accounts.utils.AccountNumberGenerator;

import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository,
                              AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public AccountPageDto getAllAccounts(Integer pageNumber, Integer pageSize) {

        log.info("Fetching accounts with pageNumber={} and pageSize={}", pageNumber, pageSize);

        if (pageNumber < 0) pageNumber = 0;
        if (pageSize <= 0) pageSize = 10;

        Pageable pageable = PageRequest.of(pageNumber, pageSize,
                Sort.by("accountNumber").ascending());

        Page<Account> accounts = accountRepository.findAll(pageable);

        Page<AccountResponseDto> dtoPage = accounts.map(accountMapper::toDto);

        log.info("Fetched {} accounts", dtoPage.getNumberOfElements());

        return AccountPageDto.builder()
                .accounts(dtoPage.getContent())
                .noOfElements(dtoPage.getNumberOfElements())
                .totalElements(dtoPage.getTotalElements())
                .isFirst(dtoPage.isFirst())
                .isLast(dtoPage.isLast())
                .build();
    }

    @Override
    public AccountResponseDto getAccountByAccountNumber(Long accountNumber) {

        log.info("Fetching account with accountNumber={}", accountNumber);

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> {
                    log.error("Account not found for accountNumber={}", accountNumber);
                    return new ApplicationException(
                            "Account number " + accountNumber + " does not exist",
                            "ACCOUNT_NOT_FOUND",
                            HttpStatus.NOT_FOUND
                    );
                });

        log.info("Account fetched successfully for accountNumber={}", accountNumber);

        return accountMapper.toDto(account);
    }

    @Override
    public AccountResponseDto createAccount(AccountRequestDto accountRequestDto) {

        log.info("Creating account for email={} phone={}",
                accountRequestDto.getEmail(),
                accountRequestDto.getPhone());

        try {

            Account account = accountMapper.toEntity(accountRequestDto);

            if (accountRepository.findByEmail(account.getEmail()).isPresent()) {

                log.warn("Account creation failed: email already exists={}", account.getEmail());

                throw new ApplicationException(
                        "Account with given email id " + account.getEmail() + " already present",
                        "ACCOUNT_CREATION_FAILED",
                        HttpStatus.NOT_ACCEPTABLE
                );
            }

            if (accountRepository.findByPhone(account.getPhone()).isPresent()) {

                log.warn("Account creation failed: phone already exists={}", account.getPhone());

                throw new ApplicationException(
                        "Account with given phone number " + account.getPhone() + " already present",
                        "ACCOUNT_CREATION_FAILED",
                        HttpStatus.NOT_ACCEPTABLE
                );
            }

            account.setAccountNumber(AccountNumberGenerator.generate());

            Account createdAccount = accountRepository.save(account);

            log.info("Account created successfully with accountNumber={}",
                    createdAccount.getAccountNumber());

            return accountMapper.toDto(createdAccount);

        } catch (ApplicationException e) {

            log.error("Application exception during account creation: {}", e.getMessage());
            throw e;

        } catch (Exception e) {

            log.error("Unexpected error during account creation", e);

            throw new ApplicationException(
                    "Account creation failed",
                    "ACCOUNT_CREATION_FAILED",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Override
    public void deleteAccountByAccountNumber(Long accountNumber) {

        log.info("Deleting account with accountNumber={}", accountNumber);

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> {
                    log.error("Account deletion failed: account not found {}", accountNumber);
                    return new ApplicationException(
                            "Account number " + accountNumber + " does not exist",
                            "ACCOUNT_NOT_FOUND",
                            HttpStatus.NOT_FOUND
                    );
                });

        try {

            accountRepository.delete(account);

            log.info("Account deleted successfully accountNumber={}", accountNumber);

        } catch (Exception e) {

            log.error("Error deleting account accountNumber={}", accountNumber, e);

            throw new ApplicationException(
                    "Account deletion failed",
                    "ACCOUNT_DELETION_FAILED",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Override
    public AccountResponseDto updateAccountByAccountNumber(Long accountNumber, AccountUpdateDto dto) {

        log.info("Updating account with accountNumber={}", accountNumber);

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> {
                    log.error("Account update failed: account not found {}", accountNumber);
                    return new ApplicationException(
                            "Account number " + accountNumber + " does not exist",
                            "ACCOUNT_NOT_FOUND",
                            HttpStatus.NOT_FOUND
                    );
                });

        try {

            if (dto.getEmail() != null && !dto.getEmail().isBlank()) {
                log.info("Updating email for accountNumber={}", accountNumber);
                account.setEmail(dto.getEmail());
            }

            if (dto.getPhone() != null && !dto.getPhone().isBlank()) {
                log.info("Updating phone for accountNumber={}", accountNumber);
                account.setPhone(dto.getPhone());
            }

            Account updatedAccount = accountRepository.save(account);

            log.info("Account updated successfully accountNumber={}", accountNumber);

            return accountMapper.toDto(updatedAccount);

        } catch (Exception e) {

            log.error("Error updating account accountNumber={}", accountNumber, e);

            throw new ApplicationException(
                    "Account update failed",
                    "ACCOUNT_UPDATE_FAILED",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Override
    @Transactional
    public AccountResponseDto debit(TransactionRequestDto transactionRequestDto) {

        log.info("Debit request received accountNumber={} amount={}",
                transactionRequestDto.getAccountNumber(),
                transactionRequestDto.getAmount());

        Account account = accountRepository.findByAccountNumber(
                        transactionRequestDto.getAccountNumber())
                .orElseThrow(() -> {
                    log.error("Debit failed: account not found {}",
                            transactionRequestDto.getAccountNumber());
                    return new ApplicationException(
                            "Account number " + transactionRequestDto.getAccountNumber() + " does not exist",
                            "ACCOUNT_NOT_FOUND",
                            HttpStatus.NOT_FOUND
                    );
                });

        if (transactionRequestDto.getAmount() <= 0) {

            log.warn("Invalid debit amount={}", transactionRequestDto.getAmount());

            throw new ApplicationException(
                    "Invalid transaction amount",
                    "INVALID_AMOUNT",
                    HttpStatus.BAD_REQUEST
            );
        }

        if (account.getBalance() < transactionRequestDto.getAmount()) {

            log.warn("Insufficient balance accountNumber={} balance={} requestedAmount={}",
                    account.getAccountNumber(),
                    account.getBalance(),
                    transactionRequestDto.getAmount());

            throw new ApplicationException(
                    "Insufficient balance",
                    "INSUFFICIENT_BALANCE",
                    HttpStatus.BAD_REQUEST
            );
        }

        account.setBalance(account.getBalance() - transactionRequestDto.getAmount());

        Account updatedAccount = accountRepository.save(account);

        log.info("Debit successful accountNumber={} newBalance={}",
                updatedAccount.getAccountNumber(),
                updatedAccount.getBalance());

        return accountMapper.toDto(updatedAccount);
    }

    @Override
    @Transactional
    public AccountResponseDto credit(TransactionRequestDto transactionRequestDto) {

        log.info("Credit request received accountNumber={} amount={}",
                transactionRequestDto.getAccountNumber(),
                transactionRequestDto.getAmount());

        Account account = accountRepository.findByAccountNumber(
                        transactionRequestDto.getAccountNumber())
                .orElseThrow(() -> {
                    log.error("Credit failed: account not found {}",
                            transactionRequestDto.getAccountNumber());
                    return new ApplicationException(
                            "Account number " + transactionRequestDto.getAccountNumber() + " does not exist",
                            "ACCOUNT_NOT_FOUND",
                            HttpStatus.NOT_FOUND
                    );
                });

        if (transactionRequestDto.getAmount() <= 0) {

            log.warn("Invalid credit amount={}", transactionRequestDto.getAmount());

            throw new ApplicationException(
                    "Invalid transaction amount",
                    "INVALID_AMOUNT",
                    HttpStatus.BAD_REQUEST
            );
        }

        account.setBalance(account.getBalance() + transactionRequestDto.getAmount());

        Account updatedAccount = accountRepository.save(account);

        log.info("Credit successful accountNumber={} newBalance={}",
                updatedAccount.getAccountNumber(),
                updatedAccount.getBalance());

        return accountMapper.toDto(updatedAccount);
    }
}