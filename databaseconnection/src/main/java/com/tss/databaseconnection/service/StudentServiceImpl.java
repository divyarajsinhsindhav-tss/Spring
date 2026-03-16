package com.tss.databaseconnection.service;

import com.tss.databaseconnection.dto.request.StudentRequestDto;
import com.tss.databaseconnection.dto.response.StudentPageDto;
import com.tss.databaseconnection.dto.response.StudentResponseDto;
import com.tss.databaseconnection.entity.Student;
import com.tss.databaseconnection.exception.ApplicationException;
import com.tss.databaseconnection.mapper.StudentMapper;
import com.tss.databaseconnection.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private StudentMapper studentMapper;

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    public StudentServiceImpl(StudentRepository studentRepository,  StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public StudentPageDto findAll(Integer pageSize, Integer pageNumber) {

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

    @Override
    public StudentResponseDto findById(int id) {
        Student student = studentRepository.findById(id).get();
        return studentMapper.toDto(student);
    }

    @Override
    public StudentResponseDto addStudent(StudentRequestDto student) {

        logger.info("Adding new student to database. firstName={}, age={}",
                student.getFirstName(), student.getAge());

        try {

            Student newStudent = studentMapper.toEntity(student);

            studentRepository.save(newStudent);

            logger.info("Student successfully saved with id={}", newStudent.getStudentId());

            return studentMapper.toDto(newStudent);

        } catch (Exception e) {

            logger.error("Error occurred while saving student with firstName={}",
                    student.getFirstName(), e);

            throw e;
        }
    }

    @Override
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    @Override
    public StudentResponseDto updateStudent(int id, StudentRequestDto updatedStudent) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setFirstName(updatedStudent.getFirstName());
        student.setAge(updatedStudent.getAge());
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toDto(savedStudent);
    }

    @Override
    public StudentPageDto findByFirstName(String firstName, Integer pageSize, Integer pageNumber) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Student> students = studentRepository.findByFirstName(firstName, pageable);

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
