package com.farhan.serviceapp.controller;

import com.farhan.serviceapp.model.ServiceCategory;
import com.farhan.serviceapp.repository.ServiceCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ServiceCategoryRepository serviceCategoryRepository;

    // ✅ Add a new service
    @PostMapping("/services")
    public ResponseEntity<?> addService(@RequestBody ServiceCategory service) {
        if (serviceCategoryRepository.findByName(service.getName()).isPresent()) {
            return ResponseEntity.badRequest().body("Service with this name already exists.");
        }
        return ResponseEntity.ok(serviceCategoryRepository.save(service));
    }

    // ✅ Get all services
    @GetMapping("/services")
    public ResponseEntity<List<ServiceCategory>> getAllServices() {
        return ResponseEntity.ok(serviceCategoryRepository.findAll());
    }
}
