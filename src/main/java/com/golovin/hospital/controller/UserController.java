package com.golovin.hospital.controller;

import com.golovin.hospital.dto.request.UserRequest;
import com.golovin.hospital.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("new/registration")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    public String create(@RequestBody UserRequest request) {
        userService.registration(request);
        return "Пользователь зарегистрирован!";
    }
}
