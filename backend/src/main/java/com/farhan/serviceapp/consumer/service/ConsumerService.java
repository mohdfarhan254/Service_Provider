// 📁 src/main/java/com/farhan/serviceapp/consumer/service/ConsumerService.java

package com.farhan.serviceapp.consumer.service;

import com.farhan.serviceapp.consumer.dto.ProviderFeedbackRequest; // DTO for submitting provider feedback
import com.farhan.serviceapp.consumer.dto.ProviderInfoDTO;         // DTO for provider info response
import com.farhan.serviceapp.consumer.dto.ServiceResponseDTO;      // DTO for listing service categories
import com.farhan.serviceapp.core.entity.ProviderFeedback;         // Entity representing feedback for providers
import com.farhan.serviceapp.core.entity.User;                     // Represents a user (typically a consumer here)

import java.util.List;

public interface ConsumerService { // Interface defining all consumer-related operations

    List<ServiceResponseDTO> getAllServices(); 
    // Returns list of all available services

    List<ProviderInfoDTO> getProvidersByService(Long serviceId); 
    // Returns providers associated with a specific service

    void leaveFeedback(Long providerId, User consumer, ProviderFeedbackRequest request); 
    // Allows consumer to submit feedback for a provider

    List<ProviderFeedback> getFeedbacksForProvider(Long providerId); 
    // Returns all feedback entries for a specific provider

    void bookProvider(User consumer, Long providerId); 
    // Allows consumer to book a specific provider
}
