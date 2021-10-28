package com.golovin.hospital.dao.helper;

import com.golovin.hospital.dao.entity.Patient;
import com.golovin.hospital.exception.NotFoundException;
import com.golovin.hospital.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Component
@RequiredArgsConstructor
@Transactional(propagation = Propagation.MANDATORY)
public class PatientHelper {

    private final PatientRepository patientRepository;

    public Patient findPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Patient with " + id + " not found"));
    }
}
