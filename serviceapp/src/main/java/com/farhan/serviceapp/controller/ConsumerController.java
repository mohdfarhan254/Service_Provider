package com.farhan.serviceapp.controller;

import com.farhan.serviceapp.dto.ProviderInfoDTO;
import com.farhan.serviceapp.dto.ServiceResponseDTO;
import com.farhan.serviceapp.service.ConsumerService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumer")
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerService consumerService;

    // ✅ View All Services
    @GetMapping("/services")
    public ResponseEntity<List<ServiceResponseDTO>> getAllServices() {
        return ResponseEntity.ok(consumerService.getAllServices());
    }

    // ✅ View Providers under a Service
    @GetMapping("/providers/{serviceId}")
    public ResponseEntity<List<ProviderInfoDTO>> getProvidersByService(@PathVariable Long serviceId) {
        return ResponseEntity.ok(consumerService.getProvidersByService(serviceId));
    }
}
