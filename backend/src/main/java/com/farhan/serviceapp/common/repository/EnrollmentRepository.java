package com.farhan.serviceapp.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farhan.serviceapp.common.entity.ProviderEnrollment;

@Repository
public interface EnrollmentRepository
        extends JpaRepository<ProviderEnrollment, Long> { 
            List<ProviderEnrollment> findByServiceId(Long serviceId);

        }
