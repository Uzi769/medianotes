package com.golovin.hospital.service;


import com.golovin.hospital.dto.request.UserRequest;

import java.util.Optional;

public interface UserService {

    void registration(UserRequest userRequest);

    Optional<UserRequest> findAuthenticationInfo(String email);
}
