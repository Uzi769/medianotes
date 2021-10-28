package com.golovin.hospital.service.impl;

import com.golovin.hospital.dao.entity.Event;
import com.golovin.hospital.dao.mapper.EventMapper;
import com.golovin.hospital.dto.response.EventResponse;
import com.golovin.hospital.dto.response.MedicalStaffResponse;
import com.golovin.hospital.dto.response.VisitResponse;
import com.golovin.hospital.repository.EventRepository;
import com.golovin.hospital.service.EventService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    private final EventMapper eventMapper;

    @Override
    public void createEvent(JoinPoint joinPoint, Object value) {
        Event event = null;
        String methodName = joinPoint.getSignature().getName();
        switch (methodName) {
            case "createVisit", "updateVisit" -> event = getEvent((VisitResponse) value, methodName);
            default -> throw new IllegalStateException("Unexpected value: " + methodName);
        }
        eventRepository.save(event);
    }

    private Event getEvent(VisitResponse visitResponse, String methodName) {
        Event event = new Event();
        if (methodName.equals("createVisit")) {
            event.setDescription("Создано посещение для " + visitResponse.getPatientName() + ", принимающий " + visitResponse.getMedicalsName());
        } else {
            event.setDescription("Изменено посещение для " + visitResponse.getPatientName() + ", принимающий " + visitResponse.getMedicalsName());
        }

        event.setDate(Instant.now());
        return event;
    }

    public List<EventResponse> getAll() {
        return eventRepository.findAll()
                .stream()
                .map(eventMapper::eventToEventResponse)
                .collect(Collectors.toList());
    }
}
