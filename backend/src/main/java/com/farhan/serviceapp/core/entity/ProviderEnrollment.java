// 📁 src/main/java/com/farhan/serviceapp/common/entity/ProviderEnrollment.java
package com.farhan.serviceapp.core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "provider_enrollments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProviderEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "provider_id")
    private User provider;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private ServiceCategory service;

    private String phone;
    private String address;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "provider_images", joinColumns = @JoinColumn(name = "enrollment_id"))
    @Column(name = "image_url")
    private List<String> images;

    private String availability;
}
