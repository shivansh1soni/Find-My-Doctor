package com.shivansh.myapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Entity
@Table(name = "patients")
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, message = "Name should be at least 3 characters long")
    private String name;

    @NotBlank(message = "City is mandatory")
    @Size(max = 20, message = "City should be at most 20 characters long")
    private String city;

    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is mandatory")
    @Size(min = 10, message = "Phone number should be at least 10 digits")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Symptom symptom;

    public enum Symptom {
        ARTHRITIS, BACK_PAIN, TISSUE_INJURIES, DYSMENORRHEA,
        SKIN_INFECTION, SKIN_BURN, EAR_PAIN
    }
}
