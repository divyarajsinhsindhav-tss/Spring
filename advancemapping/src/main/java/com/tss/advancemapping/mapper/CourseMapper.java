package com.tss.advancemapping.mapper;

import com.tss.advancemapping.dto.request.CourseRequestDto;
import com.tss.advancemapping.dto.response.CourseResponseDto;
import com.tss.advancemapping.entity.Course;
import com.tss.advancemapping.entity.Instructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course toEntity(CourseRequestDto courseRequestDto);

    @Mapping(source = "instructor.name", target = "instructorName")
    @Mapping(source = "instructor.instructor_id", target = "instructorId")
    CourseResponseDto toDto(Course course);
}
