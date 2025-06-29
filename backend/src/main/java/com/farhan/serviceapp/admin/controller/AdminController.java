// 📁 src/main/java/com/farhan/serviceapp/admin/controller/AdminController.java

package com.farhan.serviceapp.admin.controller;

import com.farhan.serviceapp.common.entity.ServiceCategory;
import com.farhan.serviceapp.common.entity.User;
import com.farhan.serviceapp.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/services")
    public ResponseEntity<?> addService(@RequestBody ServiceCategory service) {
        try {
            return ResponseEntity.ok(adminService.addService(service));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/services")
    public ResponseEntity<List<ServiceCategory>> getAllServices() {
        return ResponseEntity.ok(adminService.getAllServices());
    }

    @GetMapping("/providers")
    public ResponseEntity<List<User>> getAllProviders() {
        return ResponseEntity.ok(adminService.getAllProviders());
    }
}
