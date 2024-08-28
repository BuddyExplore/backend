package com.example.demo.repo;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

    List<User> findByRole(Role role);
}
