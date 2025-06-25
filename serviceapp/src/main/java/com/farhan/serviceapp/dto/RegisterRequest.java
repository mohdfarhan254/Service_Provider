package com.farhan.serviceapp.dto;

import com.farhan.serviceapp.model.Role;
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
