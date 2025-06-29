// 📁 src/main/java/com/farhan/serviceapp/admin/service/impl/AdminServiceImpl.java

package com.farhan.serviceapp.admin.service.impl;

import com.farhan.serviceapp.admin.service.AdminService;
import com.farhan.serviceapp.common.entity.Role;
import com.farhan.serviceapp.common.entity.ServiceCategory;
import com.farhan.serviceapp.common.entity.User;
import com.farhan.serviceapp.common.repository.ServiceCategoryRepository;
import com.farhan.serviceapp.common.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final ServiceCategoryRepository serviceCategoryRepository;
    private final UserRepository userRepository;

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
