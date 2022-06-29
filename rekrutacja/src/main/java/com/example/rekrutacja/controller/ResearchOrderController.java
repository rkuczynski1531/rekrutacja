package com.example.rekrutacja.controller;

import com.example.rekrutacja.model.ResearchOrder;
import com.example.rekrutacja.model.Result;
import com.example.rekrutacja.service.ResearchOrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/order")
public class ResearchOrderController {

    private final ResearchOrderService researchOrderService;

    public ResearchOrderController(ResearchOrderService researchOrderService) {
        this.researchOrderService = researchOrderService;
    }

    @GetMapping("/{id}")
    public ResearchOrder getOrder(@PathVariable("id") Long id){
        return researchOrderService.getResearchOrder(id);
    }

    @GetMapping()
    public List<ResearchOrder> getAllOrders(){
        return researchOrderService.getAllResearchOrders();
    }

//    @PostMapping("/patient/{patientId}/project/{projectId}")
//    public void addOrder(@PathVariable("patientId") Long patientId,
//                         @PathVariable("projectId") Long projectId,
//                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime time,
//                         @RequestParam String title){
//        researchOrderService.addOrder(patientId, projectId, time, title);
//    }

    @PostMapping("/patient/{patientId}/project/{projectId}")
    public void addOrder(@PathVariable("patientId") Long patientId,
                         @PathVariable("projectId") Long projectId,
                         @RequestBody ResearchOrder researchOrder){
        researchOrderService.addOrder(patientId, projectId, researchOrder);
    }
}
