package com.golovin.hospital.controller;

import com.golovin.hospital.aspect.EventInfo;
import com.golovin.hospital.dto.request.VisitRequest;
import com.golovin.hospital.dto.response.VisitResponse;
import com.golovin.hospital.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visit")
@CrossOrigin
@RequiredArgsConstructor
public class VisitController {

    private final VisitService visitService;

    @GetMapping("/medical/{id}")
    public List<VisitResponse> getVisitByMedicalId(@PathVariable Long id){
        return visitService.findVisitByMedical(id);
    }

    @GetMapping("/patient/{id}")
    public List<VisitResponse> getVisitByPatientId(@PathVariable Long id){
        return visitService.findVisitByPatient(id);
    }

    @EventInfo
    @PostMapping("/new")
    public VisitResponse createVisit(@RequestBody VisitRequest request){
        return visitService.createVisit(request);
    }

    @EventInfo
    @PutMapping("/{id}")
    public VisitResponse updateVisitById(@PathVariable Long id,
                                       @RequestBody VisitRequest request) {
        return visitService.updateVisit(request, id);
    }
}
