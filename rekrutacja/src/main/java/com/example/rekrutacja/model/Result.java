package com.example.rekrutacja.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @JsonBackReference(value = "result-patient")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "research_order_id", nullable = false)
    @JsonBackReference(value = "result-order")
    private ResearchOrder researchOrder;
}
