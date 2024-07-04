package com.shivansh.myapp.service;

import com.shivansh.myapp.entity.Patient;
import com.shivansh.myapp.exception.ResourceNotFoundException;
import com.shivansh.myapp.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient addPatient(Patient patient) {
        // You can add any custom validation here before saving
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id " + id));
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}