package com.learnify.backend.masterservice.repository;

import com.learnify.backend.common.constants.Role;
import com.learnify.backend.masterservice.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
    Boolean existsByIdAndRole(Integer id, Role role);
}
