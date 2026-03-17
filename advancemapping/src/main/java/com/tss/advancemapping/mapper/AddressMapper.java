package com.tss.advancemapping.mapper;

import com.tss.advancemapping.dto.request.AddressRequestDto;
import com.tss.advancemapping.dto.response.AddressResponseDto;
import com.tss.advancemapping.entity.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address toEntity(AddressRequestDto addressRequestDto);
    AddressResponseDto toDto(Address address);
}
