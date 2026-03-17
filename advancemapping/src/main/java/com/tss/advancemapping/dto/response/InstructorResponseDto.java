package com.tss.advancemapping.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class InstructorResponseDto {
    private String name;
    private String qualification;

    private List<CourseResponseDto> courses;
}
