package com.shivansh.myapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "doctors")
@Getter
@Setter
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, message = "Name should be at least 3 characters long")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "city")
    private City city;

    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is mandatory")
    @Size(min = 10, message = "Phone number should be at least 10 digits")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "speciality")
    private Speciality speciality;

    public enum City {
        DELHI, NOIDA, FARIDABAD
    }

    public enum Speciality {
        ORTHOPAEDIC, GYNECOLOGY, DERMATOLOGY, ENT_SPECIALIST
    }
}