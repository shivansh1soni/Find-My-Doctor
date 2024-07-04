package com.shivansh.myapp.service;

import com.shivansh.myapp.entity.Doctor;
import com.shivansh.myapp.exception.ResourceNotFoundException;
import com.shivansh.myapp.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor addDoctor(Doctor doctor) {
        // You can add any custom validation here before saving
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id " + id));
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    public List<Doctor> getDoctorsByCityAndSpeciality(Doctor.City city, Doctor.Speciality speciality) {
        return doctorRepository.findByCityAndSpeciality(city, speciality);
    }
}