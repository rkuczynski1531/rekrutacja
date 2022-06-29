package com.example.rekrutacja.service;

import com.example.rekrutacja.model.Consent;
import com.example.rekrutacja.model.Patient;
import com.example.rekrutacja.model.Project;
import com.example.rekrutacja.repository.ConsentRepository;
import com.example.rekrutacja.repository.PatientRepository;
import com.example.rekrutacja.repository.ProjectRepository;
import org.apache.tomcat.util.http.fileupload.MultipartStream;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
public class ConsentService {

    private final ConsentRepository consentRepository;
    private final PatientRepository patientRepository;
    private final ProjectRepository projectRepository;

    public ConsentService(ConsentRepository consentRepository, PatientRepository patientRepository, ProjectRepository projectRepository) {
        this.consentRepository = consentRepository;
        this.patientRepository = patientRepository;
        this.projectRepository = projectRepository;
    }

    public Consent getConsent(Long id){
        return consentRepository.getReferenceById(id);
    }

    public List<Consent> getAllConsents(){
        return consentRepository.findAll();
    }

    public void addConsent(MultipartFile multipartFile,
                           Long patientId,
                           Long projectId) throws IOException {

        if (!patientRepository.existsById(patientId))
            throw new IllegalStateException("patient doesn't exist");
        if (!projectRepository.existsById(projectId))
            throw new IllegalStateException("project doesn't exist");
//        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        Patient patient = patientRepository.getReferenceById(patientId);
        Project project = projectRepository.getReferenceById(projectId);
        if (consentRepository.existsByPatientAndProject(patient, project))
            throw new IllegalStateException("there is already consent for this patient and project");
        String extension = getExtension(multipartFile);
        String fileName = patient.getName() + patient.getSurname() + patientId + "Project" + projectId + "." + extension;

        Consent consent = new Consent(fileName, patient, project);
        consentRepository.save(consent);
        uploadFile(multipartFile, consent);
    }

    private String getExtension(MultipartFile multipartFile){
        String extension = "";

        int i = Objects.requireNonNull(multipartFile.getOriginalFilename()).lastIndexOf('.');
        if (i > 0) {
            extension = multipartFile.getOriginalFilename().substring(i+1);
        }
        System.out.println(extension);
        if (!extension.equals("pdf") && !extension.equals("jpg"))
            throw new IllegalStateException("bad file extension");
        return extension;
    }

    private void uploadFile(MultipartFile multipartFile, Consent consent) throws IOException {
        String uploadDir = "consents";
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(consent.getFileName());
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + consent.getFileName(), ioe);
        }
    }

    public void updateConsent(MultipartFile multipartFile,
                            Long consentId,
                            Long patientId,
                            Long projectId) throws IOException {
        if (!consentRepository.existsById(consentId))
            throw new IllegalStateException("consent doesn't exist");
        if (patientId != null && !patientRepository.existsById(patientId))
            throw new IllegalStateException("patient doesn't exist");
        if (projectId != null && !projectRepository.existsById(projectId))
            throw new IllegalStateException("project doesn't exist");

        Consent consent = consentRepository.getReferenceById(consentId);
        if (patientId != null && !patientId.equals(consent.getPatient().getId())){
            Patient patient = patientRepository.getReferenceById(patientId);
            consent.setPatient(patient);
            String fileName = patient.getName() + patient.getSurname() + patientId + "Project" + projectId;
            consent.setFileName(fileName);
        }
        if (projectId != null && !projectId.equals(consent.getProject().getId())){
            Project project = projectRepository.getReferenceById(projectId);
            consent.setProject(project);
        }
        if (!multipartFile.isEmpty()){
            uploadFile(multipartFile, consent);
        }
        String extension = getExtension(multipartFile);
        Patient patient = consent.getPatient();
        String fileName = patient.getName() + patient.getSurname() + patientId + "Project" + projectId + "." + extension;
        consent.setFileName(fileName);
        consentRepository.save(consent);
    }

    public void deleteConsent(Long consentId){
        if (!consentRepository.existsById(consentId))
            throw new IllegalStateException("consent doesn't exist");
        Consent consent = consentRepository.getReferenceById(consentId);
        Project project = consent.getProject();
        Patient patient = consent.getPatient();
        project.getPatients().remove(patient);
        consentRepository.deleteById(consentId);
    }
}
