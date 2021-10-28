package com.golovin.hospital.repository;

import com.golovin.hospital.dao.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Long> {
}
