package com.golovin.hospital.dao.mapper;

import com.golovin.hospital.dao.entity.MedicalStaff;
import com.golovin.hospital.dao.entity.Patient;
import com.golovin.hospital.dao.helper.MedicalStaffHelper;
import com.golovin.hospital.dto.request.PatientRequest;
import com.golovin.hospital.dto.response.PatientResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class PatientMapper {
    @Autowired
    private MedicalStaffHelper medicalStaffHelper;

    @Mapping(target = "medicals", ignore = true)
    public abstract Patient patientRequestToPatient(PatientRequest patientRequest);

    @Mapping(target = "medicals", ignore = true)
    public abstract PatientResponse patientToPatientResponse(Patient patient);

    @AfterMapping
    protected void map(@MappingTarget PatientResponse patientResponse, Patient patient) {
        if (patient.getMedicals() != null) {
            List<String> medicalName = patient.getMedicals()
                    .stream()
                    .map(MedicalStaff::getName)
                    .collect(Collectors.toList());

            patientResponse.setMedicals(medicalName);
        }
    }

    @AfterMapping
    protected void map(@MappingTarget Patient patient, PatientRequest patientRequest) {
        if (patientRequest.getMedicalsId() != null) {
            List<MedicalStaff> medicals = patientRequest.getMedicalsId()
                    .stream()
                    .map(id -> medicalStaffHelper.findMedicalById(id))
                    .collect(Collectors.toList());

            patient.setMedicals(medicals);
        }
    }
}
