// 📁 src/main/java/com/farhan/serviceapp/admin/controller/AdminController.java

package com.farhan.serviceapp.admin.controller;

import com.farhan.serviceapp.admin.service.AdminService; // Service layer for admin operations
import com.farhan.serviceapp.core.entity.ServiceCategory; // Entity representing a service category
import com.farhan.serviceapp.core.entity.User;            // Entity representing a user (including providers)

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000") // Allow requests from the frontend running on port 3000
@RestController // Marks this class as a REST controller
@RequestMapping("/api/admin") // Base path for all admin-related endpoints
@RequiredArgsConstructor // Lombok: generates constructor for final fields
public class AdminController {

    private final AdminService adminService; // Injected admin service for handling business logic

    @PostMapping("/services") // POST endpoint to add a new service
    public ResponseEntity<?> addService(@RequestBody ServiceCategory service) {
        try {
            return ResponseEntity.ok(adminService.addService(service)); // Add service and return result
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Handle invalid service input
        }
    }

    @GetMapping("/services") // GET endpoint to fetch all services
    public ResponseEntity<List<ServiceCategory>> getAllServices() {
        return ResponseEntity.ok(adminService.getAllServices()); // Return list of service categories
    }

    @GetMapping("/providers") // GET endpoint to fetch all users with PROVIDER role
    public ResponseEntity<List<User>> getAllProviders() {
        return ResponseEntity.ok(adminService.getAllProviders()); // Return list of provider users
    }
}
