package com.golovin.hospital.service.impl;

import com.golovin.hospital.dao.entity.Role;
import com.golovin.hospital.dao.entity.User;
import com.golovin.hospital.dto.request.UserRequest;
import com.golovin.hospital.repository.RoleRepository;
import com.golovin.hospital.repository.UserRepository;
import com.golovin.hospital.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Override
    public void registration(UserRequest userRequest) {
        User user = userRepository.findByName(userRequest.getName());
        if (user == null) {
            userRepository.save(customMapper(userRequest));
        } else {
            throw new NotFoundException("Такой пользователь уже существует!");
        }
        System.out.println(123);
    }

    @Override
    public Optional<UserRequest> findAuthenticationInfo(String name) {
        Optional<User> userOpt = userRepository.findOneWithRolesByName(name);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return Optional.of(new UserRequest(
                    user.getId(),
                    user.getName(),
                    user.getPassword(),
                    user.getRoles().stream().map(Role::getCode).collect(Collectors.toSet())
            ));
        } else {
            return Optional.empty();
        }
    }

    public User customMapper(UserRequest userRequest) {
        Set<Long> setCode = userRequest.getRoleCodes()
                .stream()
                .map(Long::parseLong)
                .collect(Collectors.toSet());

        Set<Role> setRole = setCode.stream()
                .map(a -> roleRepository.findById(a)
                        .orElseThrow(() -> new NotFoundException("Role with id " + a + " not found")))
                .collect(Collectors.toSet());

        User user = new User();
        user.setPassword(userRequest.getPassword());
        user.setName(userRequest.getName());
        user.setRoles(setRole);
        return user;
    }
}
