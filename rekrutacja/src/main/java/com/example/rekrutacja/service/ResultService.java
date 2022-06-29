package com.example.rekrutacja.service;

import com.example.rekrutacja.model.Patient;
import com.example.rekrutacja.model.ResearchOrder;
import com.example.rekrutacja.model.Result;
import com.example.rekrutacja.repository.PatientRepository;
import com.example.rekrutacja.repository.ResearchOrderRepository;
import com.example.rekrutacja.repository.ResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {

    private final ResultRepository resultRepository;
    private final ResearchOrderRepository researchOrderRepository;
    private final PatientRepository patientRepository;


    public ResultService(ResultRepository resultRepository, ResearchOrderRepository researchOrderRepository, PatientRepository patientRepository) {
        this.resultRepository = resultRepository;
        this.researchOrderRepository = researchOrderRepository;
        this.patientRepository = patientRepository;
    }

    public Result getResult(Long id){
        return resultRepository.getReferenceById(id);
    }

    public List<Result> getAllResults(){
        return resultRepository.findAll();
    }

    public void addResult(Long orderId, Long patientId, Result result){
        if (!researchOrderRepository.existsById(orderId))
            throw new IllegalStateException("Order doesn't exist");
        if (!patientRepository.existsById(patientId))
            throw new IllegalStateException("patient doesn't exist");

        ResearchOrder researchOrder = researchOrderRepository.getReferenceById(orderId);
        Patient patient = patientRepository.getReferenceById(patientId);
        result.setResearchOrder(researchOrder);
        result.setPatient(patient);
        resultRepository.save(result);
    }
    public void deleteResult(Long resultId){
        if (resultRepository.existsById(resultId))
            resultRepository.deleteById(resultId);
        else
            throw new IllegalStateException("result doesn't exist");
    }

    public void updateResult(Long resultId, String title, Long patientId, Long orderId){
        if (!resultRepository.existsById(resultId))
            throw new IllegalStateException("result doesn't exist");

        Result result = resultRepository.getReferenceById(resultId);
        if (title != null && title.length() > 0){
            result.setTitle(title);
        }

        if (patientId != null){
            if (!patientRepository.existsById(patientId))
                throw new IllegalStateException("patient doesn't exist");
            else
                result.setPatient(patientRepository.getReferenceById(patientId));
        }

        if (orderId != null){
            if (!researchOrderRepository.existsById(orderId))
                throw new IllegalStateException("research order doesn't exist");
            else
                result.setResearchOrder(researchOrderRepository.getReferenceById(orderId));
        }
        resultRepository.save(result);

    }
}
