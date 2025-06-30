package com.farhan.serviceapp.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farhan.serviceapp.core.entity.ServiceCategory;

import java.util.Optional;

public interface adminRepository extends JpaRepository<ServiceCategory, Long> {
    Optional<ServiceCategory> findByName(String name);
}
