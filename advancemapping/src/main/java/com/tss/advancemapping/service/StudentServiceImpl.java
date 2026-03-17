package com.tss.advancemapping.service;

import com.tss.advancemapping.dto.request.StudentRequestDto;
import com.tss.advancemapping.dto.response.StudentPageDto;
import com.tss.advancemapping.dto.response.StudentResponseDto;
import com.tss.advancemapping.entity.Student;
import com.tss.advancemapping.mapper.StudentMapper;
import com.tss.advancemapping.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository,  StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }


    @Override
    public StudentResponseDto addNewStudent(StudentRequestDto studentRequestDto) {
        try {

            Student newStudent = studentMapper.toEntity(studentRequestDto);

            studentRepository.save(newStudent);

            return studentMapper.toDto(newStudent);

        } catch (Exception e) {

            throw e;
        }
    }

    @Override
    public StudentPageDto findAll(Integer pageNumber, Integer pageSize) {
        PageRequest pageable = PageRequest.of(pageNumber, pageSize);

        Page<Student> students = studentRepository.findAll(pageable);

        Page<StudentResponseDto> dtoPage = students.map(studentMapper::toDto);

        return StudentPageDto.builder()
                .content(dtoPage.getContent())
                .noOfElements(dtoPage.getNumberOfElements())
                .totalElements(dtoPage.getTotalElements())
                .totalPage(dtoPage.getTotalPages())
                .isFirst(dtoPage.isFirst())
                .isLast(dtoPage.isLast())
                .build();
    }
}
