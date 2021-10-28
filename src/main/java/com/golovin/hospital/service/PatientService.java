package com.golovin.hospital.service;

import com.golovin.hospital.dao.entity.Patient;
import com.golovin.hospital.dto.request.PatientRequest;
import com.golovin.hospital.dto.response.PatientResponse;

import java.util.List;

public interface PatientService {

    List<PatientResponse> getAll();

    PatientResponse create(PatientRequest request);

    PatientResponse update(PatientRequest request, Long id);

    List<PatientResponse> delete(Long id);

    PatientResponse findPatientById(Long id);

    void updatePatient(Patient patient, PatientRequest patientRequest);
}
