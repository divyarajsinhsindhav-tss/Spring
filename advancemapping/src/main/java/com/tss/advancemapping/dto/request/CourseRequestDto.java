package com.tss.advancemapping.dto.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CourseRequestDto {

    private String name;
    private Double fees;
    private Integer duration;

    private Integer instructorId;
}
