package com.golovin.hospital.repository;

import com.golovin.hospital.dao.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);

//    Optional<User> findByName(String username);


    @EntityGraph("User.roles")
    Optional<User> findOneWithRolesByName(String name);

    @EntityGraph("User.roles")
    @Query("select u from User u")
    List<User> findAllWithRoles();
}
