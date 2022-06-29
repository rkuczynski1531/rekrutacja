package com.example.rekrutacja.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "projects_patients",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id")
    )
    @JsonManagedReference(value = "patient-project")
    private Set<Patient> patients = new HashSet<>();

    @OneToMany(mappedBy = "project")
    @JsonManagedReference(value = "order-project")
    private Set<ResearchOrder> researchOrders = new HashSet<>();

    @OneToMany(mappedBy = "project")
    @JsonManagedReference(value = "consent-project")
    private Set<Consent> consents = new HashSet<>();
}
