package com.shivansh.myapp.repository;

import com.shivansh.myapp.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // No custom methods needed initially, but can be added later
}