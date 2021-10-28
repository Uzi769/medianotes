package com.golovin.hospital.dao.mapper;

import com.golovin.hospital.dao.entity.MedicalStaff;
import com.golovin.hospital.dao.entity.Patient;
import com.golovin.hospital.dao.entity.Visit;
import com.golovin.hospital.dao.helper.MedicalStaffHelper;
import com.golovin.hospital.dao.helper.PatientHelper;
import com.golovin.hospital.dto.request.VisitRequest;
import com.golovin.hospital.dto.response.VisitResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class VisitMapper {
    @Autowired
    private MedicalStaffHelper medicalStaffHelper;
    @Autowired
    private PatientHelper patientHelper;

    @Mapping(target = "medical", ignore = true)
    @Mapping(target = "patient", ignore = true)
    public abstract Visit visitRequestToVisit(VisitRequest visitRequest);

    @Mapping(target = "medicalsName", ignore = true)
    @Mapping(target = "patientName", ignore = true)
    public abstract VisitResponse visitToVisitResponse(Visit visit);

    @AfterMapping
    protected void map(@MappingTarget Visit visit, VisitRequest visitRequest) {
        Patient patient = patientHelper.findPatientById(visitRequest.getPatientId());
        MedicalStaff medicalStaff = medicalStaffHelper.findMedicalById(visitRequest.getMedicalsId());
        visit.setPatient(patient);
        visit.setMedical(medicalStaff);
    }

    @AfterMapping
    protected void map(@MappingTarget VisitResponse visitResponse, Visit visit) {
        visitResponse.setMedicalsName(visit.getMedical().getName());
        visitResponse.setPatientName(visit.getPatient().getName());
    }
}

