package com.example.rekrutacja.service;

import com.example.rekrutacja.model.ResearchOrder;
import com.example.rekrutacja.model.Patient;
import com.example.rekrutacja.model.Project;
import com.example.rekrutacja.repository.ResearchOrderRepository;
import com.example.rekrutacja.repository.PatientRepository;
import com.example.rekrutacja.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final ProjectRepository projectRepository;
    private final ResearchOrderRepository researchOrderRepository;

    public PatientService(PatientRepository patientRepository, ProjectRepository projectRepository, ResearchOrderRepository researchOrderRepository) {
        this.patientRepository = patientRepository;
        this.projectRepository = projectRepository;
        this.researchOrderRepository = researchOrderRepository;
    }

    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }

    public Patient getPatient(Long id){
        return patientRepository.getReferenceById(id);
    }

    public void addPatient(Patient patient){
        if (patientRepository.findPatientByEmail(patient.getEmail()).isPresent()){
            throw new IllegalStateException("email taken");
        }
        patientRepository.save(patient);
    }

    public void updatePatient(Long id,
                              String name,
                              String surname,
                              Integer age,
                              LocalDate birthday,
                              String phoneNumber,
                              String email,
                              String password){
        if (patientRepository.existsById(id)){
            Patient patient = patientRepository.getReferenceById(id);

            if (name != null &&
                    name.length() > 0 &&
                    !name.equals(patient.getName()))
                patient.setName(name);
            System.out.println(patient.getName());
            if (surname != null &&
                    surname.length() > 0 &&
                    !surname.equals(patient.getSurname()))
                patient.setSurname(surname);

            if (age != null && age != patient.getAge())
                patient.setAge(age);

            if(birthday != null && !birthday.isEqual(patient.getBirthday()))
                patient.setBirthday(birthday);

            if (phoneNumber != null && !phoneNumber.equals(patient.getPhoneNumber()))
                patient.setPhoneNumber(phoneNumber);

            if (email != null && !email.equals(patient.getEmail()) && !patientRepository.existsByEmail(email))
                patient.setEmail(email);

            if (password != null && !password.equals(patient.getPassword()))
                patient.setPassword(password);

            patientRepository.save(patient);
        }

    }

    public void deletePatient(Long id){
        if (patientRepository.existsById(id))
            patientRepository.deleteById(id);
        else
            throw new IllegalStateException("patient doesn't exist");
    }


}
