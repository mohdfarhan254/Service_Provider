// 📁 src/main/java/com/farhan/serviceapp/auth/service/AuthService.java

package com.farhan.serviceapp.auth.service;

import com.farhan.serviceapp.auth.dto.*;

public interface AuthService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    void sendOtp(String email);
    void verifyOtpOnly(OtpVerificationRequest request);
    void resetPasswordAfterOtp(OtpPasswordResetRequest request);
}
