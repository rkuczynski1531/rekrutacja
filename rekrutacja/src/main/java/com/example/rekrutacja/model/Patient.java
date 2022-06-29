package com.example.rekrutacja.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "patients")
public class Patient extends Userr {

    @ManyToMany(mappedBy = "patients")
    @JsonIgnore
    private Set<Project> projects = new HashSet<>();

    @OneToMany(mappedBy = "patient")
    @JsonManagedReference(value = "order-patient")
    private Set<ResearchOrder> researchOrders = new HashSet<>();
    @OneToMany(mappedBy = "patient")
    @JsonManagedReference(value = "consent-patient")
    private Set<Consent> consents = new HashSet<>();

}
