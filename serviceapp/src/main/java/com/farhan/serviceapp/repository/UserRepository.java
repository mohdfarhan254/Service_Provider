
package com.farhan.serviceapp.repository;

import com.farhan.serviceapp.model.Role;
import com.farhan.serviceapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByRole(Role role);

}
