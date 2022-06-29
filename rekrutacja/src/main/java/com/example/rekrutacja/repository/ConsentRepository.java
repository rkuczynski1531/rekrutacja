package com.example.rekrutacja.repository;

import com.example.rekrutacja.model.Consent;

import com.example.rekrutacja.model.Patient;
import com.example.rekrutacja.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsentRepository extends JpaRepository<Consent, Long> {

    boolean existsByPatientAndProject(Patient patient, Project project);
}
