// 📁 com.farhan.serviceapp.repository.ProviderFeedbackRepository.java
package com.farhan.serviceapp.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farhan.serviceapp.core.entity.ProviderFeedback;
import com.farhan.serviceapp.core.entity.User;

import java.util.List;

@Repository
public interface consumerRepository extends JpaRepository<ProviderFeedback, Long> {
    List<ProviderFeedback> findByProvider(User provider); // cleaner method
}
