package com.farhan.serviceapp.repository;

import com.farhan.serviceapp.model.ProviderEnrollment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository
        extends JpaRepository<ProviderEnrollment, Long> { 
            List<ProviderEnrollment> findByServiceId(Long serviceId);

        }
