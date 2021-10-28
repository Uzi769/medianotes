package com.golovin.hospital.dao.mapper;

import com.golovin.hospital.dao.entity.MedicalStaff;
import com.golovin.hospital.dao.entity.Patient;
import com.golovin.hospital.dao.entity.Visit;
import com.golovin.hospital.dao.helper.DirectionHelper;
import com.golovin.hospital.dto.request.MedicalStaffRequest;
import com.golovin.hospital.dto.response.MedicalStaffResponse;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class MedicalStaffMapper {

    @Autowired
    protected DirectionHelper directionHelper;

    @Mapping(target = "direction", ignore = true)
    public abstract MedicalStaff medicalStaffRequestToMedicalStaff(MedicalStaffRequest request);

    @Mappings({
            @Mapping(target = "patients", ignore = true),
            @Mapping(target = "visits", ignore = true),
            @Mapping(target = "nameDirection", ignore = true)

    })
    public abstract MedicalStaffResponse medicalStaffToMedicalResponse(MedicalStaff medicalStaff);

    @AfterMapping
    protected void map(@MappingTarget MedicalStaff medicalStaff, MedicalStaffRequest request) {
        medicalStaff.setDirection(directionHelper.findNameDirectionByID(request.getDirectionId()));
    }

    @AfterMapping
    protected void map(@MappingTarget MedicalStaffResponse medicalResponse, MedicalStaff medicalStaff) {
        if (medicalStaff.getPatients() != null) {
            List<String> namePatients = medicalStaff.getPatients()
                    .stream()
                    .map(Patient::getName)
                    .collect(Collectors.toList());

            medicalResponse.setPatients(namePatients);
        }

        if (medicalStaff.getVisits() != null) {
            Map<LocalDate, String> visitsMap = medicalStaff.getVisits().stream()
                    .collect(Collectors.toMap(Visit::getDateVisit, visit -> visit.getPatient().getName()));

            medicalResponse.setVisits(visitsMap);
        }
        medicalResponse.setNameDirection(medicalStaff.getDirection().getName());
    }
}
