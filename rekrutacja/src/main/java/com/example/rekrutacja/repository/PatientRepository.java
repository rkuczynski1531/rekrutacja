package com.example.rekrutacja.repository;

import com.example.rekrutacja.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findPatientByEmail(String email);

    boolean existsByEmail(String email);
}
