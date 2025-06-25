package com.farhan.serviceapp.service;

import com.farhan.serviceapp.dto.EnrollServiceRequest;
import com.farhan.serviceapp.model.*;
import com.farhan.serviceapp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProviderService {

    private final ServiceCategoryRepository serviceCategoryRepository;
    private final EnrollmentRepository      enrollmentRepository;

    public void enrollProvider(User provider, EnrollServiceRequest req) {

        // 1️⃣ make sure service exists
        ServiceCategory service = serviceCategoryRepository.findById(req.getServiceId())
                .orElseThrow(() -> new IllegalArgumentException("Service not found"));

        // 2️⃣ create & save enrollment
        ProviderEnrollment enrollment = ProviderEnrollment.builder()
                .provider(provider)
                .service(service)
                .phone(req.getPhone())
                .address(req.getAddress())
                .images(req.getImages())
                .availability(req.getAvailability())
                .build();

        enrollmentRepository.save(enrollment);
    }
}
