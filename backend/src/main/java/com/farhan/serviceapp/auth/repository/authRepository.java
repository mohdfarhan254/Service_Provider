package com.farhan.serviceapp.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farhan.serviceapp.core.entity.User;
import com.farhan.serviceapp.core.enums.Role;

import java.util.List;
import java.util.Optional;

public interface authRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByRole(Role role);

    // ✅ NEW: Get all users by role
    List<User> findByRole(Role role);
}
