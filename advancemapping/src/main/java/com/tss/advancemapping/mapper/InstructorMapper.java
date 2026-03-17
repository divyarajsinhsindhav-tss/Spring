package com.tss.advancemapping.mapper;

import com.tss.advancemapping.dto.request.InstructorRequestDto;
import com.tss.advancemapping.dto.response.InstructorResponseDto;
import com.tss.advancemapping.entity.Instructor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InstructorMapper {
    Instructor toEntity(InstructorRequestDto instructorRequestDto);
    InstructorResponseDto toDto(Instructor instructor);
}
