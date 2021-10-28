package com.golovin.hospital.dao.mapper;

import com.golovin.hospital.dao.entity.*;
import com.golovin.hospital.dto.request.UserRequest;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public abstract User userRequestToUser(UserRequest userRequest);
}

