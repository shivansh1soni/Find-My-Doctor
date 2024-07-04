package com.shivansh.myapp.controller;

import com.shivansh.myapp.entity.Doctor;
import com.shivansh.myapp.exception.InvalidCityException;
import com.shivansh.myapp.exception.NoDoctorFoundException;
import com.shivansh.myapp.service.DoctorSuggestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/suggest-doctor")
public class DoctorSuggestionController {

    private final DoctorSuggestionService doctorSuggestionService;

    public DoctorSuggestionController(DoctorSuggestionService doctorSuggestionService) {
        this.doctorSuggestionService = doctorSuggestionService;
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> suggestDoctors(@RequestParam Long patientId) throws NoDoctorFoundException, InvalidCityException {
        List<Doctor> doctors = doctorSuggestionService.suggestDoctors(patientId);
        return ResponseEntity.ok(doctors);
    }
}