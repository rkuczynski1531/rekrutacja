package com.example.rekrutacja.service;

import com.example.rekrutacja.model.Patient;
import com.example.rekrutacja.model.Project;
import com.example.rekrutacja.model.ResearchOrder;
import com.example.rekrutacja.model.Result;
import com.example.rekrutacja.repository.PatientRepository;
import com.example.rekrutacja.repository.ProjectRepository;
import com.example.rekrutacja.repository.ResearchOrderRepository;
import com.example.rekrutacja.repository.ResultRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResearchOrderService {

    private final ResearchOrderRepository researchOrderRepository;
    private final PatientRepository patientRepository;
    private final ProjectRepository projectRepository;

    public ResearchOrderService(ResearchOrderRepository researchOrderRepository, PatientRepository patientRepository, ProjectRepository projectRepository) {
        this.researchOrderRepository = researchOrderRepository;
        this.patientRepository = patientRepository;
        this.projectRepository = projectRepository;
    }

    public ResearchOrder getResearchOrder(Long id){
        return researchOrderRepository.getReferenceById(id);
    }

    public List<ResearchOrder> getAllResearchOrders(){
        return researchOrderRepository.findAll();
    }

    public void addOrder(Long patientId, Long projectId, LocalDateTime time, String title){
        if (!patientRepository.existsById(patientId))
            throw new IllegalStateException("patient doesn't exist");
        if (!projectRepository.existsById(projectId))
            throw new IllegalStateException("project doesn't exist");

        Patient patient = patientRepository.getReferenceById(patientId);
        Project project = projectRepository.getReferenceById(projectId);
        ResearchOrder researchOrder = new ResearchOrder(title, time, project, patient);
        researchOrderRepository.save(researchOrder);
        patient.getResearchOrders().add(researchOrder);
        patientRepository.save(patient);
    }
}
