// 📁 src/main/java/com/farhan/serviceapp/provider/service/ProviderService.java

package com.farhan.serviceapp.provider.controller;

import com.farhan.serviceapp.common.entity.AvailabilityStatus;
import com.farhan.serviceapp.common.entity.User;
import com.farhan.serviceapp.provider.dto.EnrollServiceRequest;

public interface ProviderService {
    void enrollProvider(User provider, EnrollServiceRequest req);

    void updateAvailability(Long providerId, AvailabilityStatus status);
}
