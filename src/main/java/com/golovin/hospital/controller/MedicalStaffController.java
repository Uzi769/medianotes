package com.golovin.hospital.controller;

import com.golovin.hospital.dto.filter.MedicalFilterDto;
import com.golovin.hospital.dto.request.MedicalStaffRequest;
import com.golovin.hospital.dto.response.MedicalStaffResponse;
import com.golovin.hospital.service.MedicalStaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/medical")
@CrossOrigin
@RequiredArgsConstructor
public class MedicalStaffController {

    private final MedicalStaffService service;

    @GetMapping
    public List<MedicalStaffResponse> getAll(){
        return service.getAll();
    }

    @PostMapping("/new")
    public MedicalStaffResponse create(@RequestBody MedicalStaffRequest request){
        return service.create(request);
    }

    @PostMapping
    public List<MedicalStaffResponse> getAll(@RequestBody Collection<MedicalFilterDto> filters) {
        return service.getAll(filters);
    }

    @PutMapping("/{id}")
    public MedicalStaffResponse update(@PathVariable("id") Long id,
                              @RequestBody MedicalStaffRequest medicalStaffRequest) {
        return service.update(medicalStaffRequest, id);
    }

    @DeleteMapping("/{id}")
    public List<MedicalStaffResponse> delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }

}
