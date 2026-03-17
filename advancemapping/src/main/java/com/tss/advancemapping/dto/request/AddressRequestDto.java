package com.tss.advancemapping.dto.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AddressRequestDto {
    private String state;
    private String city;
    private Integer pincode;
}
