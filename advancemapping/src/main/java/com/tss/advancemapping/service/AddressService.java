package com.tss.advancemapping.service;

import com.tss.advancemapping.dto.request.AddressRequestDto;
import com.tss.advancemapping.dto.response.AddressResponseDto;

public interface AddressService {
    AddressResponseDto findByStudentId(Integer id);

    AddressResponseDto updateAddress(Integer id, AddressRequestDto addressRequestDto);
}
