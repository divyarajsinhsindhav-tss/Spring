package com.tss.advancemapping.service;

import com.tss.advancemapping.dto.request.InstructorRequestDto;
import com.tss.advancemapping.dto.response.InstructorResponseDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface InstructorService {
    List<InstructorResponseDto> getAllInstructors();

    InstructorResponseDto createInstructor(InstructorRequestDto instructorRequestDto);

    InstructorResponseDto getInstructorById(Integer id);

    InstructorResponseDto updateInstructor(Integer id, InstructorRequestDto instructorRequestDto);

    List<InstructorResponseDto> searchInstructor(String searchString);
}
