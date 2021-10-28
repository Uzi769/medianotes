package com.golovin.hospital.controller;

import com.golovin.hospital.dto.request.PatientRequest;
import com.golovin.hospital.dto.response.PatientResponse;
import com.golovin.hospital.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/patient")
@CrossOrigin
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping("/new")
    public PatientResponse create(@RequestBody PatientRequest request) {
        return patientService.create(request);
    }

    @GetMapping("/{id}")
    public PatientResponse getPatientById(@PathVariable Long id) {
        return patientService.findPatientById(id);
    }

    @GetMapping
    public List<PatientResponse> getAll() {
        return patientService.getAll();
    }

    @PutMapping("/{id}")
    public PatientResponse updatePatient(@PathVariable Long id,
                                         @RequestBody PatientRequest patientRequest) {
        return patientService.update(patientRequest, id);
    }

    @DeleteMapping("/{id}")
    public List<PatientResponse> delete(@PathVariable("id") Long id) {
        return patientService.delete(id);
    }
}
