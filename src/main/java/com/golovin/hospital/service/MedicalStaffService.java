package com.golovin.hospital.service;

import com.golovin.hospital.dao.entity.MedicalStaff;
import com.golovin.hospital.dto.filter.MedicalFilterDto;
import com.golovin.hospital.dto.request.MedicalStaffRequest;
import com.golovin.hospital.dto.response.MedicalStaffResponse;

import java.util.Collection;
import java.util.List;

public interface MedicalStaffService {

    List<MedicalStaffResponse> getAll();

    List<MedicalStaffResponse> getAll(Collection<MedicalFilterDto> filters);

    MedicalStaffResponse create(MedicalStaffRequest request);

    MedicalStaffResponse update(MedicalStaffRequest request, Long id);

    List<MedicalStaffResponse> delete(Long id);

    void updateMedicalStaff(MedicalStaff medicalStaff, MedicalStaffRequest medicalStaffRequest);
}

