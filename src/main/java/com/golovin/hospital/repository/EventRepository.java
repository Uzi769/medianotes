package com.golovin.hospital.repository;

import com.golovin.hospital.dao.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
