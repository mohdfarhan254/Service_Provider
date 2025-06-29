// 📁 src/main/java/com/farhan/serviceapp/admin/service/AdminService.java

package com.farhan.serviceapp.admin.service;

import com.farhan.serviceapp.common.entity.ServiceCategory;
import com.farhan.serviceapp.common.entity.User;

import java.util.List;

public interface AdminService {

    ServiceCategory addService(ServiceCategory service);

    List<ServiceCategory> getAllServices();

    List<User> getAllProviders();
}
