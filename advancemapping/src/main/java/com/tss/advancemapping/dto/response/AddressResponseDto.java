package com.tss.advancemapping.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AddressResponseDto {
    private Long  id;
    private String city;
    private String state;
    private Integer pincode;
}
