// 📁 src/main/java/com/farhan/serviceapp/admin/service/impl/AdminServiceImpl.java

package com.farhan.serviceapp.admin.service.impl;

import com.farhan.serviceapp.admin.repository.adminRepository;
import com.farhan.serviceapp.admin.service.AdminService;
import com.farhan.serviceapp.auth.repository.authRepository;
import com.farhan.serviceapp.core.entity.ServiceCategory;
import com.farhan.serviceapp.core.entity.User;
import com.farhan.serviceapp.core.enums.Role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final adminRepository serviceCategoryRepository;
    private final authRepository userRepository;

    @Override
    public ServiceCategory addService(ServiceCategory service) {
        if (serviceCategoryRepository.findByName(service.getName()).isPresent()) {
            throw new IllegalArgumentException("Service with this name already exists.");
        }
        return serviceCategoryRepository.save(service);
    }

    @Override
    public List<ServiceCategory> getAllServices() {
        return serviceCategoryRepository.findAll();
    }

    @Override
    public List<User> getAllProviders() {
        return userRepository.findByRole(Role.PROVIDER);
    }
}
