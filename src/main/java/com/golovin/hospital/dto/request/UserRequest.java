package com.golovin.hospital.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
public class UserRequest {

    private final String name;

    private final String password;

    private final Set<String> roleCodes;

    public UserRequest(Long id, String name, String password, Set<String> roleCodes) {
        this.name = name;
        this.password = password;
        this.roleCodes = roleCodes;
    }
}
