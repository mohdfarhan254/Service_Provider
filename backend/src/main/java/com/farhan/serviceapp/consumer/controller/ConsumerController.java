// 📁 src/main/java/com/farhan/serviceapp/consumer/controller/ConsumerController.java

package com.farhan.serviceapp.consumer.controller;

import com.farhan.serviceapp.consumer.dto.ProviderFeedbackRequest; // DTO for feedback submission
import com.farhan.serviceapp.consumer.dto.ProviderInfoDTO;         // DTO for provider info response
import com.farhan.serviceapp.consumer.dto.ServiceResponseDTO;      // DTO for service response
import com.farhan.serviceapp.consumer.service.ConsumerService;     // Service layer for consumer actions
import com.farhan.serviceapp.core.entity.ProviderFeedback;         // Entity representing provider feedback
import com.farhan.serviceapp.core.entity.User;                     // Represents the logged-in user

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;          // For role-based method protection
import org.springframework.security.core.annotation.AuthenticationPrincipal; // Injects authenticated user
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000") // Allow CORS requests from frontend
@RestController // Marks this class as a REST controller
@RequestMapping("/api/consumer") // Base URL for all consumer-related endpoints
@RequiredArgsConstructor // Lombok: generates constructor for final fields
public class ConsumerController {

    private final ConsumerService consumerService; // Injected consumer service for business logic

    @GetMapping("/services") // GET endpoint to fetch all available services
    public ResponseEntity<List<ServiceResponseDTO>> getAllServices() {
        return ResponseEntity.ok(consumerService.getAllServices()); // Return list of services
    }

    @GetMapping("/providers/{serviceId}") // GET endpoint to fetch providers by service ID
    public ResponseEntity<List<ProviderInfoDTO>> getProvidersByService(@PathVariable Long serviceId) {
        return ResponseEntity.ok(consumerService.getProvidersByService(serviceId)); // Return providers for given service
    }

    @PostMapping("/providers/{providerId}/feedback") // POST endpoint to submit feedback for a provider
    @PreAuthorize("hasRole('CONSUMER')") // Only users with CONSUMER role can access
    public ResponseEntity<String> leaveFeedback(
            @PathVariable Long providerId, // Provider ID to whom feedback is being given
            @AuthenticationPrincipal User consumer, // Inject the authenticated consumer
            @RequestBody ProviderFeedbackRequest request) { // Request body with feedback details

        consumerService.leaveFeedback(providerId, consumer, request); // Delegate to service
        return ResponseEntity.ok("Feedback submitted successfully."); // Return success response
    }

    @GetMapping("/providers/{providerId}/feedback") // GET endpoint to fetch all feedbacks for a provider
    public ResponseEntity<List<ProviderFeedback>> getProviderFeedbacks(@PathVariable Long providerId) {
        return ResponseEntity.ok(consumerService.getFeedbacksForProvider(providerId)); // Return list of feedbacks
    }

    @PostMapping("/book/{providerId}") // POST endpoint to book a provider
    @PreAuthorize("hasRole('CONSUMER')") // Only consumers can book providers
    public ResponseEntity<String> bookProvider(
            @AuthenticationPrincipal User consumer, // Inject authenticated consumer
            @PathVariable Long providerId) { // ID of provider to be booked

        consumerService.bookProvider(consumer, providerId); // Book provider via service
        return ResponseEntity.ok("Provider booked successfully."); // Return confirmation
    }
}
