package com.example.rekrutacja.controller;

import com.example.rekrutacja.model.Patient;
import com.example.rekrutacja.service.PatientService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<Patient> getAllPatients(){
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public Patient getPatient(@PathVariable("id") Long id){
        return patientService.getPatient(id);
    }

    @PostMapping
    public void addPatient(@RequestBody Patient patient){
        patientService.addPatient(patient);
    }

    @PutMapping("/{id}")
    public void updatePatient(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) LocalDate birthday,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String password
            ){
        patientService.updatePatient(id, name, surname, age, birthday, phoneNumber, email, password);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable("id") Long id){
        patientService.deletePatient(id);
    }


}
