// 📁 com.farhan.serviceapp.dto.AuthenticationResponse.java
package com.farhan.serviceapp.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;
    private String role;
}
