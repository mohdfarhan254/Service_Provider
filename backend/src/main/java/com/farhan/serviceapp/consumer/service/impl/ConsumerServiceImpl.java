// 📁 src/main/java/com/farhan/serviceapp/consumer/service/impl/ConsumerServiceImpl.java

package com.farhan.serviceapp.consumer.service.impl;

import com.farhan.serviceapp.admin.repository.adminRepository; // Repository to access service categories
import com.farhan.serviceapp.auth.repository.authRepository;   // Repository to access user information
import com.farhan.serviceapp.consumer.dto.ProviderFeedbackRequest; // DTO for feedback submission
import com.farhan.serviceapp.consumer.dto.ProviderInfoDTO;         // DTO for returning provider info
import com.farhan.serviceapp.consumer.dto.ServiceResponseDTO;      // DTO for returning service info
import com.farhan.serviceapp.consumer.repository.consumerRepository; // Repository for storing/retrieving feedback
import com.farhan.serviceapp.consumer.service.ConsumerService;     // Interface implemented by this class
import com.farhan.serviceapp.core.entity.ProviderEnrollment;       // Entity for provider-service enrollment
import com.farhan.serviceapp.core.entity.ProviderFeedback;         // Entity for feedback details
import com.farhan.serviceapp.core.entity.User;                     // User entity (provider or consumer)
import com.farhan.serviceapp.core.enums.AvailabilityStatus;        // Enum for provider's availability
import com.farhan.serviceapp.provider.repository.providerRepository; // Repository to fetch enrollments

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service // Marks this class as a Spring-managed service component
@RequiredArgsConstructor // Lombok: automatically injects final dependencies via constructor
public class ConsumerServiceImpl implements ConsumerService {

    private final adminRepository serviceCategoryRepository; // Used to fetch service categories
    private final providerRepository enrollmentRepository;   // Used to fetch provider enrollments
    private final authRepository userRepository;             // Used to access user (provider/consumer) data
    private final consumerRepository feedbackRepository;     // Used to save and retrieve feedback

    @Override
    public List<ServiceResponseDTO> getAllServices() {
        // Fetch all services and map them to ServiceResponseDTO
        return serviceCategoryRepository.findAll().stream().map(service -> {
            ServiceResponseDTO dto = new ServiceResponseDTO();
            dto.setId(service.getId());
            dto.setName(service.getName());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ProviderInfoDTO> getProvidersByService(Long serviceId) {
        // Fetch all provider enrollments for the given service ID
        List<ProviderEnrollment> enrollments = enrollmentRepository.findByServiceId(serviceId);

        // Convert each enrollment to a ProviderInfoDTO
        return enrollments.stream().map(enrollment -> {
            ProviderInfoDTO dto = new ProviderInfoDTO();
            dto.setId(enrollment.getId());
            dto.setProviderName(enrollment.getProvider().getName());
            dto.setEmail(enrollment.getProvider().getEmail());
            dto.setPhone(enrollment.getPhone());
            dto.setAddress(enrollment.getAddress());
            dto.setImages(enrollment.getImages());
            dto.setAvailability(enrollment.getAvailability());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void leaveFeedback(Long providerId, User consumer, ProviderFeedbackRequest request) {
        // Find the provider by ID
        User provider = userRepository.findById(providerId)
                .orElseThrow(() -> new IllegalArgumentException("Provider not found"));

        // Build the ProviderFeedback entity from the request and current time
        ProviderFeedback feedback = ProviderFeedback.builder()
                .provider(provider)
                .consumer(consumer)
                .comment(request.getComment())
                .rating(request.getRating())
                .liked(request.isLiked())
                .createdAt(LocalDateTime.now())
                .build();

        feedbackRepository.save(feedback); // Save feedback to the database
    }

    @Override
    public List<ProviderFeedback> getFeedbacksForProvider(Long providerId) {
        // Fetch the provider by ID
        User provider = userRepository.findById(providerId)
                .orElseThrow(() -> new IllegalArgumentException("Provider not found"));

        return feedbackRepository.findByProvider(provider); // Return all feedback for the provider
    }

    @Override
    public void bookProvider(User consumer, Long providerId) {
        // Fetch the provider by ID
        User provider = userRepository.findById(providerId)
                .orElseThrow(() -> new IllegalArgumentException("Provider not found"));

        // If provider is already busy, prevent booking
        if (provider.getAvailabilityStatus() == AvailabilityStatus.BUSY) {
            throw new IllegalStateException("Provider is already booked (busy)");
        }

        // Set provider status to BUSY and save the change
        provider.setAvailabilityStatus(AvailabilityStatus.BUSY);
        userRepository.save(provider);
    }
}
