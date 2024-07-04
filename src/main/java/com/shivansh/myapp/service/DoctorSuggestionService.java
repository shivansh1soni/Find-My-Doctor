package com.shivansh.myapp.service;

import com.shivansh.myapp.entity.Doctor;
import com.shivansh.myapp.entity.Patient;
import com.shivansh.myapp.exception.NoDoctorFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorSuggestionService {

    private final PatientService patientService;

    private final DoctorService doctorService;

    public DoctorSuggestionService(PatientService patientService, DoctorService doctorService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    private Doctor.Speciality getSpecialityForSymptom(Patient.Symptom symptom) {
        return switch (symptom) {
            case ARTHRITIS, BACK_PAIN, TISSUE_INJURIES -> Doctor.Speciality.ORTHOPAEDIC;
            case DYSMENORRHEA -> Doctor.Speciality.GYNECOLOGY;
            case SKIN_INFECTION, SKIN_BURN -> Doctor.Speciality.DERMATOLOGY;
            case EAR_PAIN -> Doctor.Speciality.ENT_SPECIALIST;
            default -> throw new IllegalArgumentException("Unknown symptom: " + symptom);
        };
    }

    public List<Doctor> suggestDoctors(Long patientId) throws NoDoctorFoundException {
        Patient patient = patientService.getPatientById(patientId);
        Doctor.Speciality requiredSpeciality = getSpecialityForSymptom(patient.getSymptom());

        List<Doctor> doctors = doctorService.getDoctorsByCityAndSpeciality(
                Doctor.City.valueOf(patient.getCity().toUpperCase()), requiredSpeciality
        );

        if (doctors.isEmpty()) {
            throw new NoDoctorFoundException("There isn't any doctor present at your location for your symptom");
        }

        return doctors;
    }
}