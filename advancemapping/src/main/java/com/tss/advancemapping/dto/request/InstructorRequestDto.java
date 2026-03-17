package com.tss.advancemapping.dto.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class InstructorRequestDto {

    private String name;
    private String qualification;

}
