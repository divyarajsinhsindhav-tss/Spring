package com.tss.advancemapping.repository;

import com.tss.advancemapping.dto.response.InstructorResponseDto;
import com.tss.advancemapping.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
    @Query("""
    SELECT i FROM Instructor i JOIN FETCH i.courses WHERE i.instructor_id = :instructorId
    """)
    Optional<Instructor> findByIdWithCourses(@Param("instructorId") Integer instructorId);

    @Query("""
        SELECT COUNT(i) FROM Instructor i
    """)
    Optional<Integer> countOfInstructors();

    Optional<List<Instructor>> findByNameStartsWith(@Param("searchString") String searchString);
}
