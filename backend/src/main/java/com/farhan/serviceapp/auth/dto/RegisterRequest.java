package com.farhan.serviceapp.auth.dto;

import com.farhan.serviceapp.common.entity.Role;

import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private Role role;
}
