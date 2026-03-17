package com.tss.advancemapping.dto.response;

import com.tss.advancemapping.entity.Address;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class StudentResponseDto {
    private Long  id;
    private Integer rollNumber;
    private String name;
    private AddressResponseDto address;
}
