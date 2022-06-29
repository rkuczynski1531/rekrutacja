package com.example.rekrutacja.controller;

import com.example.rekrutacja.model.Result;
import com.example.rekrutacja.service.ResearchOrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/order")
public class ResearchOrderController {

    private final ResearchOrderService researchOrderService;

    public ResearchOrderController(ResearchOrderService researchOrderService) {
        this.researchOrderService = researchOrderService;
    }

    @PostMapping("/patient/{patientId}/project/{projectId}")
    public void addOrder(@PathVariable("patientId") Long patientId,
                         @PathVariable("projectId") Long projectId,
                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime time,
                         @RequestParam String title){
        researchOrderService.addOrder(patientId, projectId, time, title);
    }
}
