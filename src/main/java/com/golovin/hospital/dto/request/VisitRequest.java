package com.golovin.hospital.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class VisitRequest {

    private Long medicalsId;

    private Long patientId;

    private LocalDate dateVisit;

    private String description;
}
