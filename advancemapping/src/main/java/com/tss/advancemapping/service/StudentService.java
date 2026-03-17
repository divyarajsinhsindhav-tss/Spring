package com.tss.advancemapping.service;

import com.tss.advancemapping.dto.request.StudentRequestDto;
import com.tss.advancemapping.dto.response.StudentPageDto;
import com.tss.advancemapping.dto.response.StudentResponseDto;
import com.tss.advancemapping.entity.Student;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface StudentService {
    StudentResponseDto addNewStudent(StudentRequestDto studentRequestDto);
    StudentPageDto findAll(Integer pageSize, Integer pageNumber);

}
