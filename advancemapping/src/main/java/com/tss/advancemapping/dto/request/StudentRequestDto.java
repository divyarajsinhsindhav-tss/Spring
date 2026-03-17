package com.tss.advancemapping.dto.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class StudentRequestDto {
    private Integer rollNumber;
    private String name;
    private AddressRequestDto address;
}
