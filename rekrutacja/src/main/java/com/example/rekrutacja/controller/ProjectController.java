package com.example.rekrutacja.controller;

import com.example.rekrutacja.model.Patient;
import com.example.rekrutacja.model.Project;
import com.example.rekrutacja.model.Project;
import com.example.rekrutacja.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> getAllProjects(){
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public Project getProject(@PathVariable("id") Long id){
        return projectService.getProject(id);
    }

    @PostMapping
    public void addProject(@RequestBody Project project){
        projectService.addProject(project);
    }

    @PutMapping("/{id}")
    public void updateProject(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Set<Patient> patients
            ){
        projectService.updateProject(id, title, patients);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable("id") Long id){
        projectService.deleteProject(id);
    }

    @PutMapping("/{projectId}/patient/{patientId}")
    public void addPatientToProject(@PathVariable("projectId") Long projectId,
                                    @PathVariable("patientId") Long patientId){
        projectService.addPatientToProject(projectId, patientId);
    }

    @DeleteMapping("/{projectId}/patient/{patientId}")
    public void deletePatientFromProject(@PathVariable("projectId") Long projectId,
                                         @PathVariable("patientId") Long patientId) {
        projectService.deletePatientFromProject(projectId, patientId);
    }
}
