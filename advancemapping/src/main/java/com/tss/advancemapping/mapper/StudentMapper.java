package com.tss.advancemapping.mapper;

import com.tss.advancemapping.dto.request.StudentRequestDto;
import com.tss.advancemapping.dto.response.StudentResponseDto;
import com.tss.advancemapping.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface StudentMapper {
    Student toEntity(StudentRequestDto studentRequestDto);
    StudentResponseDto toDto(Student student);
}
