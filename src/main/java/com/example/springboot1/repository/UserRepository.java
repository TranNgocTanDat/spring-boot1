package com.example.springboot1.repository;

import com.example.springboot1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsUserByUsername(String username);
    Optional<User> findByUsername(String username);
}
