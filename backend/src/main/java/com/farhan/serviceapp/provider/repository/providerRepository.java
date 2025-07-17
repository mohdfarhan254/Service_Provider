package com.farhan.serviceapp.provider.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository; // JPA base interface for CRUD operations
import org.springframework.stereotype.Repository; // Marks this interface as a Spring repository

import com.farhan.serviceapp.core.entity.ProviderEnrollment; // Entity representing provider-service enrollment

@Repository // Indicates this is a Spring Data repository
public interface providerRepository
        extends JpaRepository<ProviderEnrollment, Long> { // Extends JpaRepository for ProviderEnrollment entity with primary key of type Long

    List<ProviderEnrollment> findByServiceId(Long serviceId); // Custom method to find all enrollments by service ID

}
