package com.golovin.hospital.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class VisitResponse {

    private String medicalsName;

    private String patientName;

    private LocalDate dateVisit;

    private String description;
}
