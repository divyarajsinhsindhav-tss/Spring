package com.tss.advancemapping.service;

import com.tss.advancemapping.dto.request.CourseRequestDto;
import com.tss.advancemapping.dto.response.CourseResponseDto;
import com.tss.advancemapping.dto.response.StudentResponseDto;
import com.tss.advancemapping.entity.Course;
import com.tss.advancemapping.entity.Instructor;
import com.tss.advancemapping.entity.Student;
import com.tss.advancemapping.exception.ApplicationException;
import com.tss.advancemapping.exception.ResourceNotFoundException;
import com.tss.advancemapping.mapper.CourseMapper;
import com.tss.advancemapping.mapper.StudentMapper;
import com.tss.advancemapping.repository.CourseRepository;
import com.tss.advancemapping.repository.InstructorRepository;
import com.tss.advancemapping.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;
    private InstructorRepository instructorRepository;
    private CourseMapper courseMapper;
    private StudentRepository studentRepository;
    private StudentMapper studentMapper;

    public CourseServiceImpl(CourseRepository courseRepository, CourseMapper courseMapper, InstructorRepository instructorRepository, StudentRepository studentRepository, StudentMapper studentMapper) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
        this.courseMapper = courseMapper;
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public CourseResponseDto createCourse(CourseRequestDto courseRequestDto) {
        try {
            return courseMapper.toDto(courseRepository.save(courseMapper.toEntity(courseRequestDto)));
        } catch (ApplicationException e) {
            throw new ApplicationException("Something went wrong while creating course", "SOMETHING_WENT_WRONG", HttpStatus.INTERNAL_SERVER_ERROR) {};
        }
    }

    @Override
    public List<CourseResponseDto> getAllCourse() {
        try {
            List<Course> courses = courseRepository.findAll();

            return courses.stream()
                    .map(courseMapper::toDto) // convert Entity → DTO
                    .collect(Collectors.toList());
        } catch (ApplicationException e) {
            throw new ApplicationException("Something went wrong while getting courses", "SOMETHING_WENT_WRONG", HttpStatus.INTERNAL_SERVER_ERROR) {};
        }
    }

    @Override
    public CourseResponseDto getCourseById(Integer id) {
        try {
            Course course = courseRepository.findById(id)
                    .orElseThrow(() -> {
                        throw new ResourceNotFoundException("Course", id);
                    });
            return courseMapper.toDto(course);
        } catch (ApplicationException e) {
            throw new ApplicationException("Something went wrong while getting courses", "SOMETHING_WENT_WRONG", HttpStatus.INTERNAL_SERVER_ERROR) {};
        }
    }

    @Override
    public CourseResponseDto updateCourse(Integer id, CourseRequestDto courseRequestDto) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", id));
        try {
            if (courseRequestDto.getName() != null) {
                course.setName(courseRequestDto.getName());
            }

            if (courseRequestDto.getFees() != null) {
                course.setFees(courseRequestDto.getFees());
            }

            if (courseRequestDto.getDuration() != null) {
                course.setDuration(courseRequestDto.getDuration());
            }

            Course updated = courseRepository.save(course);
            return courseMapper.toDto(updated);
        } catch (ApplicationException e) {
            throw new ApplicationException("Something went wrong while updating course", "SOMETHING_WENT_WRONG", HttpStatus.INTERNAL_SERVER_ERROR) {};
        }
    }

    @Override
    public CourseResponseDto assignInstructorToCourse(Integer courseId, Integer instructorId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", courseId));

        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor", instructorId));

        boolean checkedCourse = instructor.getCourses()
                .contains(course);

        if(checkedCourse) {
            return courseMapper.toDto(course);
        }

        Instructor oldInstructor = course.getInstructor();

        if(oldInstructor != null) {
            oldInstructor.getCourses().remove(course);
        }
        course.setInstructor(instructor);
        instructor.getCourses().add(course);

        Course updated = courseRepository.save(course);

        return courseMapper.toDto(updated);
    }

    @Override
    public Integer countOfCourse(Integer instructorId) {
        try {
            Integer countOfCourse = courseRepository.countOfCourses(instructorId)
                    .orElseThrow(() -> new ResourceNotFoundException("Instructor", instructorId));

            return countOfCourse;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong while fetching count of courses", "SOMETHING_WENT_WRONG", HttpStatus.INTERNAL_SERVER_ERROR) {};
        }
    }

    @Override
    public CourseResponseDto assignCourseToStudent(Integer courseId, Integer studentId) {
        try {
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new ResourceNotFoundException("Student", studentId));
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new ResourceNotFoundException("Course", courseId));

            boolean checkedStudent = student.getCourses()
                    .contains(course);

            if(checkedStudent) {
                return courseMapper.toDto(course);
            }

            course.getStudent().add(student);
            student.getCourses().add(course);

            Course updated = courseRepository.save(course);
            return courseMapper.toDto(updated);

        } catch (Exception e) {
            throw new ApplicationException("Something went wrong while assigning course to student", "SOMETHING_WENT_WRONG", HttpStatus.INTERNAL_SERVER_ERROR) {};
        }
    }

    @Override
    public List<StudentResponseDto> getStudents(Integer courseId) {
        try {
            if (!courseRepository.existsById(courseId)) {
                throw new ResourceNotFoundException("Course", courseId);
            }

            List<Student> students = courseRepository.getStudentsByCourseId(courseId)
                    .orElseThrow(() -> new ResourceNotFoundException("Student", courseId));

            List<StudentResponseDto> response = students.stream()
                    .map(studentMapper::toDto)
                    .toList();

            return response;

        } catch (Exception e) {
            throw new ApplicationException("Something went wrong while getting students",  "SOMETHING_WENT_WRONG", HttpStatus.INTERNAL_SERVER_ERROR) {};
        }
    }



}
