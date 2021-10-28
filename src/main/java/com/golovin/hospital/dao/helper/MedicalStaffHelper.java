package com.golovin.hospital.dao.helper;

import com.golovin.hospital.dao.entity.MedicalStaff;
import com.golovin.hospital.exception.NotFoundException;
import com.golovin.hospital.repository.MedicalStaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(propagation = Propagation.MANDATORY)
public class MedicalStaffHelper {

    private final MedicalStaffRepository medicalStaffRepository;

    public MedicalStaff findMedicalById(Long id) {
        return medicalStaffRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("По " + id + " не найдено совпадений"));
    }
}
