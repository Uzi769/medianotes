package com.golovin.hospital.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalStaffResponse {

    private String name;

    private String nameDirection;

    public List<String> patients;

    private Map<LocalDate, String> visits;

    private Long Cabinet;

    private List<String> codes;
}
