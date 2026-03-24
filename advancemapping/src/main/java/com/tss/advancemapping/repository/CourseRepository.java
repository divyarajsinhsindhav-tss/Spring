package com.tss.advancemapping.repository;

import com.tss.advancemapping.entity.Course;
import com.tss.advancemapping.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query("""
        SELECT COUNT(c) FROM Course c WHERE c.instructor.instructor_id = :instructorId
    """)
    Optional<Integer> countOfCourses(Integer instructorId);

    @Query("""
    SELECT s FROM Course c JOIN c.student s WHERE c.course_id = :courseId
""")
    Optional<List<Student>> getStudentsByCourseId(Integer courseId);

}
