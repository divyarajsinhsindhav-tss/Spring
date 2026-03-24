package com.tss.advancemapping.dto.response;

import com.tss.advancemapping.dto.request.InstructorRequestDto;
import com.tss.advancemapping.entity.Instructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CourseResponseDto {

    private String name;
    private Double fees;
    private Integer duration;

    private Long instructorId;
    private String instructorName;



}
