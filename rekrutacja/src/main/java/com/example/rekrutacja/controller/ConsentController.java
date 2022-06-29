package com.example.rekrutacja.controller;

import com.example.rekrutacja.service.ConsentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/consent")
public class ConsentController {

    private final ConsentService consentService;

    public ConsentController(ConsentService consentService) {
        this.consentService = consentService;
    }

    @GetMapping
    public void getAllConsents(){
        consentService.getAllConsents();
    }

    @GetMapping("/{id}")
    public void getConsent(@PathVariable("id") Long id){
        consentService.getConsent(id);
    }

    @PostMapping("/patient/{patientId}/project/{projectId}")
    public void addConsent(@RequestParam("consent") MultipartFile multipartFile,
                           @PathVariable("patientId") Long patientId,
                           @PathVariable("projectId") Long projectId) throws IOException {

        consentService.addConsent(multipartFile, patientId, projectId);
    }

    @PutMapping("/{consentId}")
    public void updateConsent(@RequestParam("consent") MultipartFile multipartFile,
                              @PathVariable("consentId") Long consentId,
                              @RequestParam(value = "patientId", required = false) Long patientId,
                              @RequestParam(value = "projectId", required = false) Long projectId) throws IOException {
        consentService.updateConsent(multipartFile, consentId, patientId, projectId);
    }

    @DeleteMapping("/{consentId}")
    public void deleteConsent(@PathVariable("consentId") Long consentId){
        consentService.deleteConsent(consentId);
    }
}
