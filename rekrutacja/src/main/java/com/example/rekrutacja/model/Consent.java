package com.example.rekrutacja.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Consent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @JsonBackReference(value = "consent-patient")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    @JsonBackReference(value = "consent-project")
    private Project project;

    public Consent(String fileName, Patient patient, Project project) {
        this.fileName = fileName;
        this.patient = patient;
        this.project = project;
    }
}
