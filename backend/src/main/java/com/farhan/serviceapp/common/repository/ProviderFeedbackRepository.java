// 📁 com.farhan.serviceapp.repository.ProviderFeedbackRepository.java
package com.farhan.serviceapp.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farhan.serviceapp.common.entity.ProviderFeedback;
import com.farhan.serviceapp.common.entity.User;

import java.util.List;

@Repository
public interface ProviderFeedbackRepository extends JpaRepository<ProviderFeedback, Long> {
    List<ProviderFeedback> findByProvider(User provider); // cleaner method
}
