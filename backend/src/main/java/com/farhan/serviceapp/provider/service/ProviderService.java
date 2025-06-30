// 📁 src/main/java/com/farhan/serviceapp/provider/service/ProviderService.java

package com.farhan.serviceapp.provider.service;

import com.farhan.serviceapp.core.entity.User;
import com.farhan.serviceapp.core.enums.AvailabilityStatus;
import com.farhan.serviceapp.provider.dto.EnrollServiceRequest;

public interface ProviderService {
    void enrollProvider(User provider, EnrollServiceRequest req);

    void updateAvailability(Long providerId, AvailabilityStatus status);
}
