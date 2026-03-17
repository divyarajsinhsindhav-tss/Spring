package com.tss.advancemapping.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StudentPageDto {
    private List<StudentResponseDto> content;
    private int noOfElements;
    private long totalElements;
    private int totalPage;
    private boolean isFirst;
    private boolean isLast;
}
