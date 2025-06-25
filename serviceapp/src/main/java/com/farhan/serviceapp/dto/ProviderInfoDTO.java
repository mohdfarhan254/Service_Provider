package com.farhan.serviceapp.dto;

import lombok.Data;
import java.util.List;

@Data
public class ProviderInfoDTO {
    private Long id;
    private String providerName;
    private String email;
    private String phone;
    private String address;
    private List<String> images;
    private String availability;
}
