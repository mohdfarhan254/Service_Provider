// 📁 src/main/java/com/farhan/serviceapp/auth/service/impl/AuthServiceImpl.java

package com.farhan.serviceapp.auth.service.impl;

import com.farhan.serviceapp.auth.dto.*;
import com.farhan.serviceapp.auth.service.AuthService;
import com.farhan.serviceapp.common.entity.*;
import com.farhan.serviceapp.common.repository.UserRepository;
import com.farhan.serviceapp.common.security.JwtService;
import com.farhan.serviceapp.common.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final EmailService emailService;

    private static final long OTP_EXPIRATION_TIME = 5 * 60 * 1000;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }

        if (request.getRole() == Role.ADMIN && userRepository.existsByRole(Role.ADMIN)) {
            throw new IllegalArgumentException("Admin already exists.");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .address(request.getAddress())
                .role(request.getRole())
                .availabilityStatus(AvailabilityStatus.AVAILABLE)
                .isVerified(false)
                .build();

        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token, user.getRole().name());
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token, user.getRole().name());
    }

    @Override
    public void sendOtp(String email) {
        userRepository.findByEmail(email).ifPresent(user -> {
            String otp = String.format("%06d", new SecureRandom().nextInt(1000000));
            user.setResetOtp(otp);
            user.setResetOtpExpiry(System.currentTimeMillis() + OTP_EXPIRATION_TIME);
            userRepository.save(user);
            emailService.sendOtpEmail(user.getEmail(), otp);
        });
    }

    @Override
    public void verifyOtpOnly(OtpVerificationRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or OTP"));

        if (user.getResetOtp() == null || !user.getResetOtp().equals(request.getOtp()) ||
            user.getResetOtpExpiry() == null || user.getResetOtpExpiry() < System.currentTimeMillis()) {
            throw new IllegalArgumentException("Invalid or expired OTP");
        }
    }

    @Override
    public void resetPasswordAfterOtp(OtpPasswordResetRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (user.getResetOtp() == null || user.getResetOtpExpiry() == null ||
            user.getResetOtpExpiry() < System.currentTimeMillis()) {
            throw new IllegalArgumentException("OTP verification expired. Please request again.");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        user.setResetOtp(null);
        user.setResetOtpExpiry(null);
        userRepository.save(user);
    }
}
