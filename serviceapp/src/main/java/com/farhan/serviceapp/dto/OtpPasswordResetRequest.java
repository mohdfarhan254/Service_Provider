package com.farhan.serviceapp.dto;

import lombok.Data;

@Data
public class OtpPasswordResetRequest {
    private String email;
    private String newPassword;
}
