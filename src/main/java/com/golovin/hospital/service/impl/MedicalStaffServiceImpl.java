package com.golovin.hospital.service.impl;

import com.golovin.hospital.dao.entity.MedicalStaff;
import com.golovin.hospital.dao.helper.DirectionHelper;
import com.golovin.hospital.dao.mapper.MedicalStaffMapper;
import com.golovin.hospital.dto.filter.MedicalFilterDto;
import com.golovin.hospital.dto.request.MedicalStaffRequest;
import com.golovin.hospital.dto.response.MedicalStaffResponse;
import com.golovin.hospital.exception.NotFoundException;
import com.golovin.hospital.repository.MedicalStaffRepository;
import com.golovin.hospital.repository.specification.MedicalSpecification;
import com.golovin.hospital.service.MedicalStaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicalStaffServiceImpl implements MedicalStaffService {

    private final MedicalStaffRepository repository;

    private final MedicalStaffMapper mapper;

    private final DirectionHelper directionHelper;

    @Override
    @Transactional(readOnly = true)
    public List<MedicalStaffResponse> getAll() {
        List<MedicalStaffResponse> medicalsResponse = repository.findAll()
                .stream()
                .map(mapper::medicalStaffToMedicalResponse)
                .collect(Collectors.toList());
        return medicalsResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicalStaffResponse> getAll(Collection<MedicalFilterDto> filters) {
        List<MedicalStaff> medicals = repository.findAll(MedicalSpecification.findMedicals(filters));
        return medicals
                .stream()
                .map(mapper::medicalStaffToMedicalResponse)
                .collect(Collectors.toList());
    }
    //flatMap - когда проблема коллекция в коллекции, выдерает внутреннюю, создает новый стрим

    @Override
    @Transactional
    public MedicalStaffResponse create(MedicalStaffRequest request) {
        MedicalStaff medicalStaff = mapper.medicalStaffRequestToMedicalStaff(request);
        repository.save(medicalStaff);
        return mapper.medicalStaffToMedicalResponse(medicalStaff);
    }

    @Override
    @Transactional
    public MedicalStaffResponse update(MedicalStaffRequest request, Long id) {
        MedicalStaff medicalStaff = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("По " + id + " не найдено совпадений"));
        updateMedicalStaff(medicalStaff, request);
        repository.save(medicalStaff);
        return mapper.medicalStaffToMedicalResponse(medicalStaff);
    }

    @Override
    @Transactional
    public List<MedicalStaffResponse> delete(Long id) {
        MedicalStaff medicalStaff = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("По " + id + " не найдено совпадений"));
        repository.delete(medicalStaff);
        return repository.findAll()
                .stream()
                .map(mapper::medicalStaffToMedicalResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void updateMedicalStaff(MedicalStaff medicalStaff, MedicalStaffRequest medicalStaffRequest) {
        if (medicalStaffRequest.getName() != null) {
            medicalStaff.setName(medicalStaffRequest.getName());
        }
        if (medicalStaffRequest.getDirectionId() != null) {
            medicalStaff.setDirection(directionHelper.findNameDirectionByID(medicalStaffRequest.getDirectionId()));
        }
        if (medicalStaffRequest.getCabinet() != null) {
            medicalStaff.setCabinet(medicalStaffRequest.getCabinet());
        }
    }
}
