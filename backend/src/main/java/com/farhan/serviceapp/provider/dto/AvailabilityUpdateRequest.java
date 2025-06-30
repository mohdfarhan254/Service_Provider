// AvailabilityUpdateRequest.java (create in dto/)
package com.farhan.serviceapp.provider.dto;

import com.farhan.serviceapp.core.enums.AvailabilityStatus;

import lombok.Data;

@Data
public class AvailabilityUpdateRequest {
    private AvailabilityStatus status;
}
