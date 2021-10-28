package com.golovin.hospital.service;

import com.golovin.hospital.dao.entity.Visit;
import com.golovin.hospital.dto.request.VisitRequest;
import com.golovin.hospital.dto.response.VisitResponse;

import java.util.List;

public interface VisitService {

    List<VisitResponse> findVisitByMedical(Long id);

    List<VisitResponse> findVisitByPatient(Long id);

    VisitResponse createVisit(VisitRequest visitRequest);

    VisitResponse updateVisit(VisitRequest visitRequest, Long id);

    void update(Visit visit, VisitRequest visitRequest);
}

