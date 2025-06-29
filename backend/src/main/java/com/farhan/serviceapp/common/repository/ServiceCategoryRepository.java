package com.farhan.serviceapp.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farhan.serviceapp.common.entity.ServiceCategory;

import java.util.Optional;

public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Long> {
    Optional<ServiceCategory> findByName(String name);
}
