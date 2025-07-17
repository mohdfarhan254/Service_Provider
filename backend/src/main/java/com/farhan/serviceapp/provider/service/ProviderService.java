// 📁 src/main/java/com/farhan/serviceapp/provider/service/ProviderService.java

package com.farhan.serviceapp.provider.service;

import com.farhan.serviceapp.core.entity.User; // Represents the authenticated user (provider)
import com.farhan.serviceapp.core.enums.AvailabilityStatus; // Enum for availability status (e.g., AVAILABLE, UNAVAILABLE)
import com.farhan.serviceapp.provider.dto.EnrollServiceRequest; // DTO for enrollment request

public interface ProviderService { // Service interface defining provider-related operations

    void enrollProvider(User provider, EnrollServiceRequest req); 
    // Method to enroll a provider to a service using provided request data

    void updateAvailability(Long providerId, AvailabilityStatus status); 
    // Method to update a provider's availability status by provider ID
}
