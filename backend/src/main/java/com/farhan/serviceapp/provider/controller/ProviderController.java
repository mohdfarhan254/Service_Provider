// 📁 src/main/java/com/farhan/serviceapp/provider/controller/ProviderController.java

package com.farhan.serviceapp.provider.controller;

import com.farhan.serviceapp.provider.dto.EnrollServiceRequest;
import com.farhan.serviceapp.provider.service.ProviderService;
import com.farhan.serviceapp.core.entity.User;
import com.farhan.serviceapp.provider.dto.AvailabilityUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/provider")
@RequiredArgsConstructor
public class ProviderController {

    private final ProviderService providerService;

    @PreAuthorize("hasRole('PROVIDER')")
    @PostMapping("/enroll")
    public ResponseEntity<String> enroll(
            @AuthenticationPrincipal User provider,
            @RequestBody EnrollServiceRequest request) {

        providerService.enrollProvider(provider, request);
        return ResponseEntity.ok("Enrolled successfully");
    }

    @PreAuthorize("hasRole('PROVIDER')")
    @PatchMapping("/availability")
    public ResponseEntity<String> updateAvailability(
            @AuthenticationPrincipal User provider,
            @RequestBody AvailabilityUpdateRequest request) {

        providerService.updateAvailability(provider.getId(), request.getStatus());
        return ResponseEntity.ok("Availability updated to " + request.getStatus());
    }
}
