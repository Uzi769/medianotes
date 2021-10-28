package com.golovin.hospital.dao.mapper;

import com.golovin.hospital.dao.entity.Event;
import com.golovin.hospital.dto.response.EventResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventResponse eventToEventResponse(Event event);
}
