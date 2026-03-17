package com.tss.advancemapping.service;

import com.tss.advancemapping.dto.request.InstructorRequestDto;
import com.tss.advancemapping.dto.response.InstructorResponseDto;
import com.tss.advancemapping.entity.Instructor;
import com.tss.advancemapping.exception.ApplicationException;
import com.tss.advancemapping.exception.ResourceNotFoundException;
import com.tss.advancemapping.mapper.InstructorMapper;
import com.tss.advancemapping.repository.InstructorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstructorServiceImpl implements InstructorService {

    private InstructorRepository instructorRepository;
    private InstructorMapper instructorMapper;

    private InstructorServiceImpl(InstructorRepository instructorRepository, InstructorMapper instructorMapper) {
        this.instructorRepository = instructorRepository;
        this.instructorMapper = instructorMapper;
    }

    @Override
    public List<InstructorResponseDto> getAllInstructors() {
        try {
            List<Instructor> instructors = instructorRepository.findAll();
            return instructors.stream()
                    .map(instructorMapper::toDto) // convert Entity → DTO
                    .collect(Collectors.toList());
        } catch (ApplicationException e) {
            throw new ApplicationException("Something went wrong while getting instructors", "SOMETHING_WENT_WRONG", HttpStatus.INTERNAL_SERVER_ERROR) {};
        }
    }

    @Override
    public InstructorResponseDto createInstructor(InstructorRequestDto instructorRequestDto) {
        try {
            return  instructorMapper.toDto(instructorRepository.save(instructorMapper.toEntity(instructorRequestDto)));
        } catch (ApplicationException e) {
            throw new ApplicationException("Something went wrong while creating Instructor", "SOMETHING_WENT_WRONG", HttpStatus.INTERNAL_SERVER_ERROR) {};
        }
    }

    @Override
    public InstructorResponseDto getInstructorById(Integer id) {
        try {
            Instructor instructor = instructorRepository.findById(id)
                    .orElseThrow(() -> {
                       throw new ResourceNotFoundException("Instructor", id);
                    });
            return instructorMapper.toDto(instructor);
        } catch (ApplicationException e) {
            throw new ApplicationException("Something went wrong while getting instructor", "SOMETHING_WENT_WRONG", HttpStatus.INTERNAL_SERVER_ERROR) {};
        }
    }

    @Override
    public InstructorResponseDto updateInstructor(Integer id, InstructorRequestDto instructorRequestDto) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor", id));
        try {
            if (instructorRequestDto.getName() != null) {
                instructor.setName(instructorRequestDto.getName());
            }
            if (instructorRequestDto.getQualification() != null) {
                instructor.setQualification(instructorRequestDto.getQualification());
            }
            Instructor updatedInstructor = instructorRepository.save(instructor);
            return instructorMapper.toDto(updatedInstructor);
        } catch (ApplicationException e) {
            throw new ApplicationException("Something went wrong while updating instructor",  "SOMETHING_WENT_WRONG", HttpStatus.INTERNAL_SERVER_ERROR) {};
        }
    }

}
