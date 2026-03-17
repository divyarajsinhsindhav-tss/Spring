package com.tss.advancemapping.controller;


import com.tss.advancemapping.dto.request.CourseRequestDto;
import com.tss.advancemapping.dto.response.CourseResponseDto;
import com.tss.advancemapping.entity.Course;
import com.tss.advancemapping.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studentapp/")
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("courses/")
    public ResponseEntity<List<CourseResponseDto>> getAllCourses() {
        return ResponseEntity.ok().body(courseService.getAllCourse());
    }

    @GetMapping("courses/{id}")
    public ResponseEntity<CourseResponseDto> getCourse(@PathVariable Integer id) {
        return ResponseEntity.ok().body(courseService.getCourseById(id));
    }

    @PostMapping("courses/")
    public ResponseEntity<CourseResponseDto> createCourse(@RequestBody CourseRequestDto courseRequestDto) {
        return ResponseEntity.ok().body(courseService.createCourse(courseRequestDto));
    }

    @PutMapping("courses/")
    public ResponseEntity<CourseResponseDto> updateCourse(@RequestBody Integer id, CourseRequestDto courseRequestDto) {
        return ResponseEntity.ok().body(courseService.updateCourse(id, courseRequestDto));
    }
}
