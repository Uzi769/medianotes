package com.golovin.hospital.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PatientRequest {

    private String name;

    private String address;

    private int age;

    private String numberPolis;

    private String numPhone;

    private List<Long> medicalsId;

}
