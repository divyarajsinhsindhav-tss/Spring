package com.tss.accounts.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class AccountNumberGenerator {

    private final static SecureRandom random = new SecureRandom();

    public static Long generate() {
        // Format: 222 + last 6 digits of millis + 3 random digits
        // Example: 222 + 123456 + 789 = 222123456789 (12 digits)
        long millis = System.currentTimeMillis() % 1000000L;   // 6 digits
        int randomPart = random.nextInt(900) + 100;             // 3 digits (100–999)

        String number = "222" + String.format("%06d", millis) + randomPart;
        return Long.parseLong(number);
    }
}