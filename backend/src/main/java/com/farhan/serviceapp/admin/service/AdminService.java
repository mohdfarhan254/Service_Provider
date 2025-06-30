// 📁 src/main/java/com/farhan/serviceapp/admin/service/AdminService.java

package com.farhan.serviceapp.admin.service;

import java.util.List;

import com.farhan.serviceapp.core.entity.ServiceCategory;
import com.farhan.serviceapp.core.entity.User;

public interface AdminService {

    ServiceCategory addService(ServiceCategory service);

    List<ServiceCategory> getAllServices();

    List<User> getAllProviders();
}
