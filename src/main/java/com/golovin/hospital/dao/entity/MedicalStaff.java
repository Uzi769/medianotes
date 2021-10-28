package com.golovin.hospital.dao.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="medical_staff")
public class MedicalStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "direction_id")
    private Direction direction;

    @ManyToMany(mappedBy = "medicals", fetch = FetchType.EAGER)
    private List<Patient> patients;

    @OneToMany(mappedBy = "medical",fetch = FetchType.LAZY)
    private List<Visit> visits;

    @Column(name = "cabinet")
    private Long cabinet;

}
