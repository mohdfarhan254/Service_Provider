// 📁 src/main/java/com/farhan/serviceapp/consumer/service/ConsumerService.java

package com.farhan.serviceapp.consumer.service;

import com.farhan.serviceapp.consumer.dto.ProviderFeedbackRequest;
import com.farhan.serviceapp.consumer.dto.ProviderInfoDTO;
import com.farhan.serviceapp.consumer.dto.ServiceResponseDTO;
import com.farhan.serviceapp.core.entity.ProviderFeedback;
import com.farhan.serviceapp.core.entity.User;

import java.util.List;

public interface ConsumerService {

    List<ServiceResponseDTO> getAllServices();

    List<ProviderInfoDTO> getProvidersByService(Long serviceId);

    void leaveFeedback(Long providerId, User consumer, ProviderFeedbackRequest request);

    List<ProviderFeedback> getFeedbacksForProvider(Long providerId);

    void bookProvider(User consumer, Long providerId);
}
