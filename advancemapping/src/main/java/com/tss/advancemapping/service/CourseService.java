package com.tss.advancemapping.service;

import com.tss.advancemapping.dto.request.CourseRequestDto;
import com.tss.advancemapping.dto.response.CourseResponseDto;

import java.util.List;

public interface CourseService {

    CourseResponseDto createCourse(CourseRequestDto courseRequestDto);

    List<CourseResponseDto> getAllCourse();

    CourseResponseDto getCourseById(Integer id);

    CourseResponseDto updateCourse(Integer id, CourseRequestDto courseRequestDto);
}
