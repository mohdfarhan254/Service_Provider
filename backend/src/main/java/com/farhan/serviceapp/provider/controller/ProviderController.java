// 📁 src/main/java/com/farhan/serviceapp/provider/controller/ProviderController.java

package com.farhan.serviceapp.provider.controller;

import com.farhan.serviceapp.provider.dto.EnrollServiceRequest; // DTO for enrollment request
import com.farhan.serviceapp.provider.service.ProviderService;   // Service layer for provider logic
import com.farhan.serviceapp.core.entity.User;                   // Authenticated user entity
import com.farhan.serviceapp.provider.dto.AvailabilityUpdateRequest; // DTO for availability update
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;  // Annotation for role-based access
import org.springframework.security.core.annotation.AuthenticationPrincipal; // To extract logged-in user
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000") // Allow CORS from frontend
@RestController // Marks this class as a REST controller
@RequestMapping("/api/provider") // Base URL path for all endpoints in this controller
@RequiredArgsConstructor // Lombok: generates constructor for final fields
public class ProviderController {

    private final ProviderService providerService; // Injected service to handle provider logic

    @PreAuthorize("hasRole('PROVIDER')") // Restrict access to users with PROVIDER role
    @PostMapping("/enroll") // POST endpoint for enrolling in a service
    public ResponseEntity<String> enroll(
            @AuthenticationPrincipal User provider, // Injects the currently authenticated provider
            @RequestBody EnrollServiceRequest request) { // Request body containing enrollment details

        providerService.enrollProvider(provider, request); // Delegates enrollment to the service
        return ResponseEntity.ok("Enrolled successfully"); // Returns 200 OK with success message
    }

    @PreAuthorize("hasRole('PROVIDER')") // Restrict access to users with PROVIDER role
    @PatchMapping("/availability") // PATCH endpoint to update availability status
    public ResponseEntity<String> updateAvailability(
            @AuthenticationPrincipal User provider, // Injects the authenticated provider
            @RequestBody AvailabilityUpdateRequest request) { // Contains new availability status

        providerService.updateAvailability(provider.getId(), request.getStatus()); // Updates status
        return ResponseEntity.ok("Availability updated to " + request.getStatus()); // Responds with new status
    }
}
