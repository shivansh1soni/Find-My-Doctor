package com.shivansh.myapp.service;

import com.shivansh.myapp.entity.Doctor;
import com.shivansh.myapp.entity.Doctor.Speciality;
import com.shivansh.myapp.entity.Patient;
import com.shivansh.myapp.exception.InvalidCityException;
import com.shivansh.myapp.exception.NoDoctorFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.shivansh.myapp.entity.Doctor.Speciality.*;

@Service
public class DoctorSuggestionService {

    private final PatientService patientService;

    private final DoctorService doctorService;

    public DoctorSuggestionService(PatientService patientService, DoctorService doctorService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    private Speciality getSpecialityForSymptom(Patient.Symptom symptom) {
        return switch (symptom) {
            case ARTHRITIS, BACK_PAIN, TISSUE_INJURIES -> ORTHOPAEDIC;
            case DYSMENORRHEA -> GYNECOLOGY;
            case SKIN_INFECTION, SKIN_BURN -> DERMATOLOGY;
            case EAR_PAIN -> ENT_SPECIALIST;
            default -> throw new IllegalArgumentException("Unknown symptom: " + symptom);
        };
    }

    public List<Doctor> suggestDoctors(Long patientId) throws NoDoctorFoundException, InvalidCityException {
        Patient patient = patientService.getPatientById(patientId);
        Speciality requiredSpeciality = getSpecialityForSymptom(patient.getSymptom());

        try {
            List<Doctor> doctors = doctorService.getDoctorsByCityAndSpeciality(
                    Doctor.City.valueOf(patient.getCity().toUpperCase()), requiredSpeciality
            );

            if (doctors.isEmpty()) {
                throw new NoDoctorFoundException("There isn't any doctor present at your location for your symptom");
            }

            return doctors;
        } catch (IllegalArgumentException e) {
            throw new InvalidCityException(e.getMessage());
        }
    }
}