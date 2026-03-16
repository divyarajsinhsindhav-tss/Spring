package com.tss.databaseconnection.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "students")
@Data
public class Student {

    @Column(name = "student_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;

    @Column(name = "first_name",  nullable = false)
    @Size(min = 1, max = 50)
    private String firstName;

    @Column(name = "age", nullable = false)
    @Min(1)
    private Integer age;
}
