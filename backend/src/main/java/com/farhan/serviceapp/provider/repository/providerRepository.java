package com.farhan.serviceapp.provider.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farhan.serviceapp.core.entity.ProviderEnrollment;

@Repository
public interface providerRepository
        extends JpaRepository<ProviderEnrollment, Long> { 
            List<ProviderEnrollment> findByServiceId(Long serviceId);

        }
