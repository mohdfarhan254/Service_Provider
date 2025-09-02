// 📁 src/main/java/com/farhan/serviceapp/auth/controller/AuthController.java

package com.farhan.serviceapp.auth.controller;

import com.farhan.serviceapp.auth.dto.*;
import com.farhan.serviceapp.auth.service.AuthService;
import com.farhan.serviceapp.shared.service.EmailService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final EmailService emailService;

    @PostMapping("/register")// a controller method will return a complete HTTP response, 
                             // allowing for customization of the status code, headers, and body
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            AuthenticationResponse response = authService.register(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
        }
    }

    @PostMapping("/login")//Data Transfer Object (DTO) is a design pattern 
                          //used to encapsulate and transfer data 
                          // between different layers or components of an application. 
                          // DTOs are simple Plain Old Java Objects (POJOs) 
                          // that primarily contain data fields and their 
                          // corresponding getter and setter methods, with minimal to no business logic. 
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/request-password-reset-otp")
    public ResponseEntity<String> requestOtp(@RequestBody EmailRequest request) {
        authService.sendOtp(request.getEmail());
        return ResponseEntity.ok("OTP sent to your email if user exists.");
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody OtpVerificationRequest request) {
        try {
            authService.verifyOtpOnly(request);
            return ResponseEntity.ok("OTP verified successfully.");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody OtpPasswordResetRequest request) {
        try {
            authService.resetPasswordAfterOtp(request);
            return ResponseEntity.ok("Password reset successful.");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/test-mail")
    public String testMail() {
        emailService.sendOtpEmail("mohdfarhan29102002@gmail.com", "123456");
        return "Test OTP sent!";
    }

    public static class ErrorResponse {
        private String error;
        public ErrorResponse(String error) { this.error = error; }
        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
    }
}
