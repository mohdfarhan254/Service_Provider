package com.farhan.serviceapp.controller;

import com.farhan.serviceapp.dto.EnrollServiceRequest;
import com.farhan.serviceapp.model.User;
import com.farhan.serviceapp.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/provider")
@RequiredArgsConstructor
public class ProviderController {

    private final ProviderService providerService;

    /** Provider enrols into a service already created by Admin */
    @PreAuthorize("hasRole('PROVIDER')")
    @PostMapping("/enroll")
    public ResponseEntity<String> enroll(
            @AuthenticationPrincipal User provider,
            @RequestBody EnrollServiceRequest request) {

        providerService.enrollProvider(provider, request);
        return ResponseEntity.ok("Enrolled successfully");
    }
}
