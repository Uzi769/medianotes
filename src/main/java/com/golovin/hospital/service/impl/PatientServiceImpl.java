package com.golovin.hospital.service.impl;

import com.golovin.hospital.dao.entity.MedicalStaff;
import com.golovin.hospital.dao.entity.Patient;
import com.golovin.hospital.dao.helper.MedicalStaffHelper;
import com.golovin.hospital.dao.mapper.PatientMapper;
import com.golovin.hospital.dto.request.PatientRequest;
import com.golovin.hospital.dto.response.PatientResponse;
import com.golovin.hospital.exception.NotFoundException;
import com.golovin.hospital.repository.PatientRepository;
import com.golovin.hospital.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    private final PatientMapper patientMapper;

    private final MedicalStaffHelper medicalStaffHelper;

    @Transactional
    @Override
    public List<PatientResponse> getAll() {
        return patientRepository.findAll()
                .stream()
                .map(patientMapper::patientToPatientResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public PatientResponse create(PatientRequest request) {
        Patient patient = patientMapper.patientRequestToPatient(request);
        patientRepository.save(patient);
        return patientMapper.patientToPatientResponse(patient);
    }

    @Transactional
    @Override
    public PatientResponse findPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("По " + id + " не найдено совпадений"));
        return patientMapper.patientToPatientResponse(patient);
    }

    @Transactional
    @Override
    public List<PatientResponse> delete(Long id) {
        Patient patient = patientRepository.findById(id).
                orElseThrow(() -> new NotFoundException("По " + id + " не найдено совпадений"));
        patientRepository.delete(patient);
        return patientRepository.findAll()
                .stream()
                .map(patientMapper::patientToPatientResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public PatientResponse update(PatientRequest patientRequest, Long id) {
        Patient patient = patientRepository.findById(id).
                orElseThrow(() -> new NotFoundException("По " + id + " не найдено совпадений"));
        updatePatient(patient, patientRequest);
        return patientMapper.patientToPatientResponse(patient);
    }

    @Override
    public void updatePatient(Patient patient, PatientRequest patientRequest) {
        if (patientRequest.getName() != null) {
            patient.setName(patientRequest.getName());
        }
        if (patientRequest.getAddress() != null) {
            patient.setAddress(patientRequest.getAddress());
        }

        if (patientRequest.getNumberPolis() != null) {
            patient.setNumberPolis(patientRequest.getNumberPolis());
        }
        if (patientRequest.getNumPhone() != null) {
            patient.setNumPhone(patientRequest.getNumPhone());
        }

        patient.setAge(patientRequest.getAge());

        if (patientRequest.getMedicalsId() != null) {
            List<MedicalStaff> medicals = patientRequest.getMedicalsId()
                    .stream()
                    .map(id -> medicalStaffHelper.findMedicalById(id))
                    .collect(Collectors.toList());

            patient.setMedicals(medicals);
        }
    }
}
