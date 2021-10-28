package com.golovin.hospital.repository;

import com.golovin.hospital.dao.entity.MedicalStaff;
import com.golovin.hospital.dao.entity.projection.MedicalIdProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MedicalStaffRepository extends JpaRepository<MedicalStaff, Long>, JpaSpecificationExecutor<MedicalStaff> {

    Optional<MedicalStaff> findByName(String name);

    MedicalIdProjection findOneByName(String name);

    @EntityGraph("MedicalStaff.roles")
    @Query("select m from MedicalStaff m")
    List<MedicalStaff> findAllWithRoles();
}
