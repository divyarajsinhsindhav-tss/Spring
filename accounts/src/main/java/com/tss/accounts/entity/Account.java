package com.tss.accounts.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "accounts")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "account_number", unique = true)
    @NotNull(message = "Account number is required")
    private Long accountNumber;

    @Column(name = "name")
    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name must contain only letters")
    @Size(min = 2, max = 50)
    private String name;

    @Column(name = "balance")
    @DecimalMin(value = "0.0", message = "Balance cannot be negative")
    private Double balance = 0.0;

    @Column(name = "email", unique = true)
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email formate. e.g. john@doe.com")
    @Size(max = 100)
    private String email;

    @Column(name = "phone", unique = true)
    @NotBlank(message = "Phone Number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Enter valid phone number.")
    private String phone;
}
