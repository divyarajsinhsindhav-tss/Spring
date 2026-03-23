package com.tss.advancemapping.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "instructors")
@Data
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instructor_id")
    private Long instructor_id;

    @Column(name = "name")
    @Size(min = 2, max = 50, message = "Name must be of size 2 to 50")
    @NotBlank(message = "Name can't be blank")
    private String name;

    @Column(name = "qualification")
    @NotBlank(message = "Qualification can't be blank")
    private String qualification;

    @OneToMany(mappedBy = "instructor", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    private List<Course> courses;
}
