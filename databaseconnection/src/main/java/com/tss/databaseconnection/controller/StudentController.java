package com.tss.databaseconnection.controller;

import com.tss.databaseconnection.dto.request.StudentRequestDto;
import com.tss.databaseconnection.dto.response.StudentPageDto;
import com.tss.databaseconnection.dto.response.StudentResponseDto;
import com.tss.databaseconnection.service.StudentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Get all students OR filter by firstName
    @GetMapping("/")
    public ResponseEntity<StudentPageDto> findAll(
            @RequestParam(required = false) String firstName,
            Integer pageSize, Integer pageNumber) {

        if (firstName == null) {
            return ResponseEntity.ok(studentService.findAll(pageSize, pageNumber));
        }

        return ResponseEntity.ok(studentService.findByFirstName(firstName, pageSize, pageNumber));
    }

    // Get student by ID
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> findById(@PathVariable int id) {
        return ResponseEntity.ok(studentService.findById(id));
    }

    // Create new student
    @PostMapping("/")
    public ResponseEntity<StudentResponseDto> createStudent(
            @Valid @RequestBody StudentRequestDto studentRequestDto) {

        logger.info("Received request to create student with firstName={}",
                studentRequestDto.getFirstName());

        StudentResponseDto response = studentService.addStudent(studentRequestDto);

        logger.info("Student created successfully with id={}", response.getId());

        return ResponseEntity.ok(response);
    }

    // Update student
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(
            @PathVariable int id,
            @Valid @RequestBody StudentRequestDto studentRequestDto) {

        return ResponseEntity.ok(studentService.updateStudent(id, studentRequestDto));
    }

    // Delete student
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {

        studentService.deleteStudent(id);

        return ResponseEntity.ok("Student with id " + id + " deleted successfully");
    }
}