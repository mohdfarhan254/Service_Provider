package com.farhan.serviceapp.dto;

import lombok.Data;
import java.util.List;

@Data
public class EnrollServiceRequest {
    private Long   serviceId;          // ID of ServiceCategory chosen
    private String phone;
    private String address;
    private List<String> images;       // URLs or Base64 strings
    private String availability;       // e.g.  "Available"  /  "Busy"
}
