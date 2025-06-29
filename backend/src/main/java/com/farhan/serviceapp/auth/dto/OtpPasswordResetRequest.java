package com.farhan.serviceapp.auth.dto;

import lombok.Data;

@Data
public class OtpPasswordResetRequest {
    private String email;
    private String newPassword;
}
