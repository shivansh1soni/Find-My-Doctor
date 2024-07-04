package com.shivansh.myapp.repository;

import com.shivansh.myapp.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    // Find doctors by city and speciality
    List<Doctor> findByCityAndSpeciality(Doctor.City city, Doctor.Speciality speciality);
}