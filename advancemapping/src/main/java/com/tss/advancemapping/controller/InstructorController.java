package com.tss.advancemapping.controller;

import com.tss.advancemapping.dto.request.InstructorRequestDto;
import com.tss.advancemapping.dto.response.InstructorResponseDto;
import com.tss.advancemapping.service.InstructorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studentapp")
public class InstructorController {

    private InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("instructor/")
    public ResponseEntity<List<InstructorResponseDto>> getAllInstructors() {
        return ResponseEntity.ok().body(instructorService.getAllInstructors());
    }

    @GetMapping("instructor/{id}")
    public ResponseEntity<InstructorResponseDto> getInstructorById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(instructorService.getInstructorById(id));
    }

    @PostMapping("instructor/")
    public ResponseEntity<InstructorResponseDto> createInstructor(@RequestBody InstructorRequestDto instructorRequestDto) {
        return ResponseEntity.ok().body(instructorService.createInstructor(instructorRequestDto));
    }

    @PutMapping("instructor/{id}")
    public ResponseEntity<InstructorResponseDto> updateInstructor(@PathVariable Integer id, @RequestBody InstructorRequestDto instructorRequestDto) {
        return ResponseEntity.ok().body(instructorService.updateInstructor(id, instructorRequestDto));
    }

}
