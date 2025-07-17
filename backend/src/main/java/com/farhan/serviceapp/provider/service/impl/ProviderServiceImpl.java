// 📁 src/main/java/com/farhan/serviceapp/provider/service/impl/ProviderServiceImpl.java

package com.farhan.serviceapp.provider.service.impl;

import com.farhan.serviceapp.admin.repository.adminRepository; // Repository for accessing service categories
import com.farhan.serviceapp.auth.repository.authRepository; // Repository for user-related operations
import com.farhan.serviceapp.core.entity.ProviderEnrollment; // Entity representing a provider's enrollment to a service
import com.farhan.serviceapp.core.entity.ServiceCategory; // Entity representing a service category
import com.farhan.serviceapp.core.entity.User; // User entity (used for authenticated provider)
import com.farhan.serviceapp.core.enums.AvailabilityStatus; // Enum for provider availability
import com.farhan.serviceapp.core.enums.Role; // Enum for user roles (e.g., PROVIDER, ADMIN)
import com.farhan.serviceapp.provider.dto.EnrollServiceRequest; // DTO for enrollment request
import com.farhan.serviceapp.provider.repository.providerRepository; // Repository for provider enrollments
import com.farhan.serviceapp.provider.service.ProviderService; // Provider service interface

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service // Marks this class as a Spring service component
@RequiredArgsConstructor // Lombok: auto-generates constructor for final fields
public class ProviderServiceImpl implements ProviderService {

    private final adminRepository serviceCategoryRepository; // Used to fetch service category by ID
    private final providerRepository enrollmentRepository;   // Used to save provider enrollment
    private final authRepository userRepository;             // Used to fetch and update user details

    @Override
    public void enrollProvider(User provider, EnrollServiceRequest req) {
        // Fetch the service category using service ID from the request
        ServiceCategory service = serviceCategoryRepository.findById(req.getServiceId())
                .orElseThrow(() -> new IllegalArgumentException("Service not found")); // Throw error if not found

        // Create a new ProviderEnrollment entity using builder pattern
        ProviderEnrollment enrollment = ProviderEnrollment.builder()
                .provider(provider)                // Set the provider
                .service(service)                  // Set the service
                .phone(req.getPhone())             // Set phone number from request
                .address(req.getAddress())         // Set address
                .images(req.getImages())           // Set images
                .availability(req.getAvailability()) // Set initial availability status
                .build();

        enrollmentRepository.save(enrollment); // Save the enrollment in the database
    }

    @Override
    public void updateAvailability(Long providerId, AvailabilityStatus status) {
        // Find the user (provider) by ID
        User user = userRepository.findById(providerId)
                .orElseThrow(() -> new IllegalArgumentException("User not found")); // Throw error if not found

        // Check if the user has a PROVIDER role
        if (user.getRole() != Role.PROVIDER) {
            throw new IllegalStateException("Only providers can update availability"); // Restrict non-providers
        }

        user.setAvailabilityStatus(status); // Update availability status
        userRepository.save(user); // Persist changes to the database
    }
}
