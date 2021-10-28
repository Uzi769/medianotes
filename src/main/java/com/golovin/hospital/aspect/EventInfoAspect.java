package com.golovin.hospital.aspect;

import com.golovin.hospital.service.EventService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class EventInfoAspect {

    private final EventService eventService;

    @AfterReturning(value = "@annotation(com.golovin.hospital.aspect.EventInfo)", returning = "value")
    public void makeSavingEvent(JoinPoint jp, Object value) {
        String methodName = jp.getSignature().getName();
        if (checkMethodName(methodName)) {
            eventService.createEvent(jp, value);
        }
    }

    private boolean checkMethodName(String methodName) {
        return methodName.contains("create") ||
                methodName.contains("update");
    }
}
