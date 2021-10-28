package com.golovin.hospital.service;

import com.golovin.hospital.dto.response.EventResponse;
import org.aspectj.lang.JoinPoint;

import java.util.List;

public interface EventService {

    void createEvent(JoinPoint joinPoint, Object value);

    List<EventResponse> getAll();
}
