package com.tss.advancemapping.controller;

import com.tss.advancemapping.dto.request.StudentRequestDto;
import com.tss.advancemapping.dto.response.CourseResponseDto;
import com.tss.advancemapping.dto.response.StudentPageDto;
import com.tss.advancemapping.dto.response.StudentResponseDto;
import com.tss.advancemapping.service.CourseService;
import com.tss.advancemapping.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studentapp/student")
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;

    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<StudentResponseDto> addNewStudent(@RequestBody StudentRequestDto studentRequestDto) {
        return ResponseEntity.ok(studentService.addNewStudent(studentRequestDto));
    }

    @GetMapping
    public ResponseEntity<StudentPageDto> getAllStudents(@RequestParam Integer pageNumber, Integer pageSize) {
        return ResponseEntity.ok().body(studentService.findAll(pageNumber, pageSize));
    }

    @PostMapping("/{studentId}/course/{courseId}/assign")
    public ResponseEntity<CourseResponseDto> assignStudent(@PathVariable Integer studentId, @PathVariable Integer courseId) {
        return ResponseEntity.ok().body(courseService.assignCourseToStudent(courseId, studentId));
    }
}
