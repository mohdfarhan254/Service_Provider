// AvailabilityUpdateRequest.java (create in dto/)
package com.farhan.serviceapp.provider.dto;

import com.farhan.serviceapp.common.entity.AvailabilityStatus;

import lombok.Data;

@Data
public class AvailabilityUpdateRequest {
    private AvailabilityStatus status;
}
