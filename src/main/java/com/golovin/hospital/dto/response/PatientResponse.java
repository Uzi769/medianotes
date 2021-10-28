package com.golovin.hospital.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponse {

    private String name;

    private String address;

    private int age;

    private String numberPolis;

    private String numPhone;

    private List<String> medicals;
}
