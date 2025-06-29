package com.farhan.serviceapp.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farhan.serviceapp.common.entity.Role;
import com.farhan.serviceapp.common.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByRole(Role role);

    // ✅ NEW: Get all users by role
    List<User> findByRole(Role role);
}
