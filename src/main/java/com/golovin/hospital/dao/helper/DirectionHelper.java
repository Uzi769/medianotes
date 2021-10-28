package com.golovin.hospital.dao.helper;

import com.golovin.hospital.dao.entity.Direction;
import com.golovin.hospital.repository.DirectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

@Component
@RequiredArgsConstructor
@Transactional(propagation = Propagation.MANDATORY)
public class DirectionHelper {
    private final DirectionRepository directionRepository;

    public Direction findNameDirectionByID(Long id){
        return directionRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Direction with id " + id + " not found"));
    }
}
