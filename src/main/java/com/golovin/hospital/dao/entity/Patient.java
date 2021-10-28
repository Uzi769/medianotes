package com.golovin.hospital.dao.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private int age;

    @Column(name = "num_polis")
    private String numberPolis;

    @Column(name = "num_phone")
    private String numPhone;

    @ManyToMany()
    @JoinTable(name = "medical_patient",
            joinColumns = {@JoinColumn(name="patient_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="medical_id", referencedColumnName = "id")})
    private List<MedicalStaff> medicals;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private List<Visit> visits;

}
