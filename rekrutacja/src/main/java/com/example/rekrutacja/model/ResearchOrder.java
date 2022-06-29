package com.example.rekrutacja.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ResearchOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private LocalDateTime time;
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    @JsonBackReference(value = "order-project")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @JsonBackReference(value = "order-patient")
    private Patient patient;

    @OneToMany(mappedBy = "researchOrder")
    @JsonManagedReference(value = "result-order")
//    @JsonIgnore
    private Set<Result> results = new HashSet<>();

    public ResearchOrder(String title, LocalDateTime time, Project project, Patient patient) {
        this.title = title;
        this.time = time;
        this.project = project;
        this.patient = patient;
    }
}
