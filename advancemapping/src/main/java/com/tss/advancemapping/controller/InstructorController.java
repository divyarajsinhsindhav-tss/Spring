package com.tss.advancemapping.controller;

import com.tss.advancemapping.dto.request.InstructorRequestDto;
import com.tss.advancemapping.dto.response.CourseResponseDto;
import com.tss.advancemapping.dto.response.InstructorResponseDto;
import com.tss.advancemapping.service.CourseService;
import com.tss.advancemapping.service.CourseServiceImpl;
import com.tss.advancemapping.service.InstructorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studentapp/instructor")
public class InstructorController {

    private InstructorService instructorService;
    private CourseService courseService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
        this.courseService = courseService;
    }

    @GetMapping("/")
    public ResponseEntity<List<InstructorResponseDto>> getAllInstructors() {
        return ResponseEntity.ok().body(instructorService.getAllInstructors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstructorResponseDto> getInstructor(@PathVariable Integer id) {
        return ResponseEntity.ok().body(instructorService.getInstructorById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<InstructorResponseDto> createInstructor(@RequestBody InstructorRequestDto instructorRequestDto) {
        return ResponseEntity.ok().body(instructorService.createInstructor(instructorRequestDto));
    }

    @PutMapping("instructor/{id}/update")
    public ResponseEntity<InstructorResponseDto> updateInstructor(@PathVariable Integer id, @RequestBody InstructorRequestDto instructorRequestDto) {
        return ResponseEntity.ok().body(instructorService.updateInstructor(id, instructorRequestDto));
    }

    @PutMapping("/{instructorId}/courses/{courseId}/assign")
    public ResponseEntity<CourseResponseDto> assignCourse(@PathVariable Integer instructorId, @PathVariable Integer courseId) {
        return ResponseEntity.ok().body(courseService.assignInstructorToCourse(instructorId, courseId));
    }

}
