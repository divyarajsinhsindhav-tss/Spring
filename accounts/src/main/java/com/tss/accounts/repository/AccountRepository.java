package com.tss.accounts.repository;

import com.tss.accounts.dto.response.AccountResponseDto;
import com.tss.accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByAccountNumber(Long accountNumber);
    Optional<Account> findByEmail(String email);
    Optional<Account> findByPhone(String accountPhone);
}
