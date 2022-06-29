package com.example.rekrutacja.controller;

import com.example.rekrutacja.model.Result;
import com.example.rekrutacja.service.ResultService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/result")
public class ResultController {

    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping
    public void getAllResults(){
        resultService.getAllResults();
    }

    @GetMapping("/{id}")
    public void getResult(@PathVariable("id") Long id){
        resultService.getResult(id);
    }

    @PostMapping("/order/{orderId}/patient/{patientId}")
    public void addResult(@PathVariable("orderId") Long orderId,
                          @PathVariable("patientId") Long patientId,
                          @RequestBody Result result){
       resultService.addResult(orderId, patientId, result);
    }

    @DeleteMapping("/{id}")
    public void deleteResult(@PathVariable("id") Long id){
        resultService.deleteResult(id);
    }

    @PutMapping("/{id}")
    public void updateResult(@PathVariable("id") Long id,
                             @RequestParam(required = false) String title,
                             @RequestParam(required = false) Long patientId,
                             @RequestParam(required = false) Long orderId){
        resultService.updateResult(id, title, patientId, orderId);
    }
}
