package com.example.rekrutacja.service;

import com.example.rekrutacja.model.Patient;
import com.example.rekrutacja.model.Project;
import com.example.rekrutacja.model.ResearchOrder;
import com.example.rekrutacja.repository.ConsentRepository;
import com.example.rekrutacja.repository.ResearchOrderRepository;
import com.example.rekrutacja.repository.PatientRepository;
import com.example.rekrutacja.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final PatientRepository patientRepository;
    private final ResearchOrderRepository researchOrderRepository;
    private final ConsentRepository consentRepository;

    public ProjectService(ProjectRepository projectRepository, PatientRepository patientRepository, ResearchOrderRepository researchOrderRepository, ConsentRepository consentRepository) {
        this.projectRepository = projectRepository;
        this.patientRepository = patientRepository;
        this.researchOrderRepository = researchOrderRepository;
        this.consentRepository = consentRepository;
    }


    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }

    public Project getProject(Long id){
        return projectRepository.getReferenceById(id);
    }

    public void addProject(Project project){
//        if (projectRepository.findProjectByEmail(project.getEmail()).isPresent()){
//            throw new IllegalStateException("email taken");
//        }
        projectRepository.save(project);
    }

    public void updateProject(Long id,
                              String title,
                              Set<Patient> patients
                              ){
        if (projectRepository.existsById(id)){
            Project project = projectRepository.getReferenceById(id);

            if (title != null &&
                    title.length() > 0 &&
                    !title.equals(project.getTitle()))
                project.setTitle(title);

            if (patients != null &&
                    patients.size() > 0)
                project.setPatients(patients);
            projectRepository.save(project);
        }

    }

    public void deleteProject(Long id){
        if (projectRepository.existsById(id))
            projectRepository.deleteById(id);
    }

    public void checkIfProjectAndPatientExist(Long projectId, Long patientId){
        if (!projectRepository.existsById(projectId))
            throw new IllegalStateException("project doesn't exist");

        else if (!patientRepository.existsById(patientId))
            throw new IllegalStateException("patient doesn't exist");
    }

    public void addPatientToProject(Long projectId, Long patientId){
        checkIfProjectAndPatientExist(projectId, patientId);
        Project project = projectRepository.getReferenceById(projectId);
        Patient patient = patientRepository.getReferenceById(patientId);
        if (consentRepository.existsByPatientAndProject(patient, project)) {
            project.getPatients().add(patient);
            projectRepository.save(project);
        }
        else
            throw new IllegalStateException("Patient didn't give consent");
    }

    public void deletePatientFromProject(Long projectId, Long patientId){
        checkIfProjectAndPatientExist(projectId, patientId);
        Project project = projectRepository.getReferenceById(projectId);
        Patient patient = patientRepository.getReferenceById(patientId);
        Set<ResearchOrder> projectOrders = project.getResearchOrders();
        for (ResearchOrder projectOrder : projectOrders) {
            if (Objects.equals(projectOrder.getPatient().getId(), patientId)) {
                project.getResearchOrders().remove(projectOrder);
                researchOrderRepository.delete(projectOrder);
            }
        }
        project.getPatients().remove(patient);
        projectRepository.save(project);
    }

}
