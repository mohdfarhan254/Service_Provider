package com.farhan.serviceapp.service;

import com.farhan.serviceapp.dto.ProviderInfoDTO;
import com.farhan.serviceapp.dto.ServiceResponseDTO;
import com.farhan.serviceapp.model.ProviderEnrollment;
import com.farhan.serviceapp.repository.EnrollmentRepository;
import com.farhan.serviceapp.repository.ServiceCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsumerService {

    private final ServiceCategoryRepository serviceCategoryRepository;
    private final EnrollmentRepository enrollmentRepository;

    public List<ServiceResponseDTO> getAllServices() {
        return serviceCategoryRepository.findAll().stream().map(service -> {
            ServiceResponseDTO dto = new ServiceResponseDTO();
            dto.setId(service.getId());
            dto.setName(service.getName());
            return dto;
        }).collect(Collectors.toList());
    }

    public List<ProviderInfoDTO> getProvidersByService(Long serviceId) {
        List<ProviderEnrollment> enrollments = enrollmentRepository.findByServiceId(serviceId);

        return enrollments.stream().map(enrollment -> {
            ProviderInfoDTO dto = new ProviderInfoDTO();
            dto.setId(enrollment.getId());
            dto.setProviderName(enrollment.getProvider().getName());
            dto.setEmail(enrollment.getProvider().getEmail());
            dto.setPhone(enrollment.getPhone());
            dto.setAddress(enrollment.getAddress());
            dto.setImages(enrollment.getImages());
            dto.setAvailability(enrollment.getAvailability());
            return dto;
        }).collect(Collectors.toList());
    }
}
