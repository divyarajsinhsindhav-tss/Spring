package com.tss.advancemapping.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "students")
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "roll_number")
    @NotNull(message = "Roll Number can't be null")
    private Integer rollNumber;

    @Column(name = "name")
    @Size(min = 2, max = 50, message = "Length of name must be between 2 to 50")
    @NotBlank(message = "Name can't be blank")
    private String name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;

}
