package com.tss.advancemapping.service;

import com.tss.advancemapping.dto.request.CourseRequestDto;
import com.tss.advancemapping.dto.response.CourseResponseDto;
import com.tss.advancemapping.dto.response.StudentResponseDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CourseService {

    CourseResponseDto createCourse(CourseRequestDto courseRequestDto);

    List<CourseResponseDto> getAllCourse();

    CourseResponseDto getCourseById(Integer id);

    CourseResponseDto updateCourse(Integer id, CourseRequestDto courseRequestDto);

    CourseResponseDto assignInstructorToCourse(Integer courseId, Integer instructorId);

    Integer countOfCourse(Integer instructorId);

    CourseResponseDto assignCourseToStudent(Integer courseId, Integer studentId);

    List<StudentResponseDto> getStudents(Integer courseId);
}
