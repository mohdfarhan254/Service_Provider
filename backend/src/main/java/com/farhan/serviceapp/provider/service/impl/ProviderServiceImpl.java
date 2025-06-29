// 📁 src/main/java/com/farhan/serviceapp/provider/service/impl/ProviderServiceImpl.java

package com.farhan.serviceapp.provider.service.impl;

import com.farhan.serviceapp.common.entity.*;
import com.farhan.serviceapp.common.repository.*;
import com.farhan.serviceapp.provider.controller.ProviderService;
import com.farhan.serviceapp.provider.dto.EnrollServiceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {

    private final ServiceCategoryRepository serviceCategoryRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;

    @Override
    public void enrollProvider(User provider, EnrollServiceRequest req) {
        ServiceCategory service = serviceCategoryRepository.findById(req.getServiceId())
                .orElseThrow(() -> new IllegalArgumentException("Service not found"));

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

    @Override
    public void updateAvailability(Long providerId, AvailabilityStatus status) {
        User user = userRepository.findById(providerId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (user.getRole() != Role.PROVIDER) {
            throw new IllegalStateException("Only providers can update availability");
        }

        user.setAvailabilityStatus(status);
        userRepository.save(user);
    }
}
