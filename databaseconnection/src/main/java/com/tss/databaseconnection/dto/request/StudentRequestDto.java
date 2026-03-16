package com.tss.databaseconnection.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class StudentRequestDto {

    @NotBlank(message = "Name cannot be blank.")
    @Size(min = 2, max = 50, message = "First name must be between 2 & 59")
    private String firstName;
    @Min(value = 1, message = "Age must be greater than or equal to 1.")
    @NotNull(message = "Age cannot be null")
    private Integer age;

}
