// 📁 src/main/java/com/farhan/serviceapp/consumer/service/impl/ConsumerServiceImpl.java

package com.farhan.serviceapp.consumer.service.impl;

import com.farhan.serviceapp.common.entity.*;
import com.farhan.serviceapp.common.repository.*;
import com.farhan.serviceapp.consumer.dto.ProviderFeedbackRequest;
import com.farhan.serviceapp.consumer.dto.ProviderInfoDTO;
import com.farhan.serviceapp.consumer.dto.ServiceResponseDTO;
import com.farhan.serviceapp.consumer.service.ConsumerService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

    private final ServiceCategoryRepository serviceCategoryRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final ProviderFeedbackRepository feedbackRepository;

    @Override
    public List<ServiceResponseDTO> getAllServices() {
        return serviceCategoryRepository.findAll().stream().map(service -> {
            ServiceResponseDTO dto = new ServiceResponseDTO();
            dto.setId(service.getId());
            dto.setName(service.getName());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ProviderInfoDTO> getProvidersByService(Long serviceId) {
        List<ProviderEnrollment> enrollments = enrollmentRepository.findByServiceId(serviceId);

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
        User provider = userRepository.findById(providerId)
                .orElseThrow(() -> new IllegalArgumentException("Provider not found"));

        ProviderFeedback feedback = ProviderFeedback.builder()
                .provider(provider)
                .consumer(consumer)
                .comment(request.getComment())
                .rating(request.getRating())
                .liked(request.isLiked())
                .createdAt(LocalDateTime.now())
                .build();

        feedbackRepository.save(feedback);
    }

    @Override
    public List<ProviderFeedback> getFeedbacksForProvider(Long providerId) {
        User provider = userRepository.findById(providerId)
                .orElseThrow(() -> new IllegalArgumentException("Provider not found"));

        return feedbackRepository.findByProvider(provider);
    }

    @Override
    public void bookProvider(User consumer, Long providerId) {
        User provider = userRepository.findById(providerId)
                .orElseThrow(() -> new IllegalArgumentException("Provider not found"));

        if (provider.getAvailabilityStatus() == AvailabilityStatus.BUSY) {
            throw new IllegalStateException("Provider is already booked (busy)");
        }

        provider.setAvailabilityStatus(AvailabilityStatus.BUSY);
        userRepository.save(provider);
    }
}
