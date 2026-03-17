package com.tss.advancemapping.controller;

import com.tss.advancemapping.dto.request.StudentRequestDto;
import com.tss.advancemapping.dto.response.StudentPageDto;
import com.tss.advancemapping.dto.response.StudentResponseDto;
import com.tss.advancemapping.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponseDto> addNewStudent(@RequestBody StudentRequestDto studentRequestDto) {
        return ResponseEntity.ok(studentService.addNewStudent(studentRequestDto));
    }

    @GetMapping
    public ResponseEntity<StudentPageDto> getAllStudents(@RequestParam Integer pageNumber, Integer pageSize) {
        return ResponseEntity.ok().body(studentService.findAll(pageNumber, pageSize));
    }

}
