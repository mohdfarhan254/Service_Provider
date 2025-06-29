// 📁 src/main/java/com/farhan/serviceapp/consumer/controller/ConsumerController.java

package com.farhan.serviceapp.consumer.controller;

import com.farhan.serviceapp.consumer.dto.ProviderFeedbackRequest;
import com.farhan.serviceapp.consumer.dto.ProviderInfoDTO;
import com.farhan.serviceapp.consumer.dto.ServiceResponseDTO;
import com.farhan.serviceapp.common.entity.ProviderFeedback;
import com.farhan.serviceapp.common.entity.User;
import com.farhan.serviceapp.consumer.service.ConsumerService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/consumer")
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerService consumerService;

    @GetMapping("/services")
    public ResponseEntity<List<ServiceResponseDTO>> getAllServices() {
        return ResponseEntity.ok(consumerService.getAllServices());
    }

    @GetMapping("/providers/{serviceId}")
    public ResponseEntity<List<ProviderInfoDTO>> getProvidersByService(@PathVariable Long serviceId) {
        return ResponseEntity.ok(consumerService.getProvidersByService(serviceId));
    }

    @PostMapping("/providers/{providerId}/feedback")
    @PreAuthorize("hasRole('CONSUMER')")
    public ResponseEntity<String> leaveFeedback(
            @PathVariable Long providerId,
            @AuthenticationPrincipal User consumer,
            @RequestBody ProviderFeedbackRequest request) {

        consumerService.leaveFeedback(providerId, consumer, request);
        return ResponseEntity.ok("Feedback submitted successfully.");
    }

    @GetMapping("/providers/{providerId}/feedback")
    public ResponseEntity<List<ProviderFeedback>> getProviderFeedbacks(@PathVariable Long providerId) {
        return ResponseEntity.ok(consumerService.getFeedbacksForProvider(providerId));
    }

    @PostMapping("/book/{providerId}")
    @PreAuthorize("hasRole('CONSUMER')")
    public ResponseEntity<String> bookProvider(
            @AuthenticationPrincipal User consumer,
            @PathVariable Long providerId) {

        consumerService.bookProvider(consumer, providerId);
        return ResponseEntity.ok("Provider booked successfully.");
    }
}
