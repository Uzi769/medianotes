package com.golovin.hospital.service.impl;

import com.golovin.hospital.dao.entity.MedicalStaff;
import com.golovin.hospital.dao.entity.Patient;
import com.golovin.hospital.dao.entity.Visit;
import com.golovin.hospital.dao.helper.MedicalStaffHelper;
import com.golovin.hospital.dao.helper.PatientHelper;
import com.golovin.hospital.dao.mapper.VisitMapper;
import com.golovin.hospital.dto.request.VisitRequest;
import com.golovin.hospital.dto.response.VisitResponse;
import com.golovin.hospital.exception.NotFoundException;
import com.golovin.hospital.repository.VisitRepository;
import com.golovin.hospital.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {

    private final VisitRepository visitRepository;

    private final MedicalStaffHelper medicalStaffHelper;

    private final PatientHelper patientHelper;

    private final VisitMapper visitMapper;

    @Override
    @Transactional(readOnly = true)
    public List<VisitResponse> findVisitByMedical(Long id) {
        MedicalStaff medicalStaff = medicalStaffHelper.findMedicalById(id);
        return medicalStaff.getVisits()
                .stream()
                .map(visitMapper::visitToVisitResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<VisitResponse> findVisitByPatient(Long id) {
        Patient patient = patientHelper.findPatientById(id);
        return patient.getVisits()
                .stream()
                .map(visitMapper::visitToVisitResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public VisitResponse createVisit(VisitRequest visitRequest) {
        Visit visit = visitMapper.visitRequestToVisit(visitRequest);
        visitRepository.save(visit);
        return visitMapper.visitToVisitResponse(visit);
    }

    @Override
    @Transactional
    public VisitResponse updateVisit(VisitRequest visitRequest, Long id) {
        Visit visit = visitRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("По " + id + " не найдено совпадений"));
        update(visit, visitRequest);
        return visitMapper.visitToVisitResponse(visit);
    }

    @Override
    public void update(Visit visit, VisitRequest visitRequest) {
        Patient patient = patientHelper.findPatientById(visitRequest.getPatientId());
        MedicalStaff medicalStaff = medicalStaffHelper.findMedicalById(visitRequest.getMedicalsId());

        visit.setMedical(medicalStaff);
        visit.setPatient(patient);
        visit.setDescription(visitRequest.getDescription());
    }
}
