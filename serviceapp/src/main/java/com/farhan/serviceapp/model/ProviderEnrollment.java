package com.farhan.serviceapp.model;

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

    /* ---------- relations ---------- */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private User provider;                      // user with role PROVIDER

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private ServiceCategory service;            // the service (Plumber, etc.)

    /* ---------- provider-specific info ---------- */
    private String phone;
    private String address;

    @ElementCollection
    @CollectionTable(name = "provider_images",
                     joinColumns = @JoinColumn(name = "enrollment_id"))
    @Column(name = "image_url")
    private List<String> images;

    private String availability;               // Available / Busy / Booked
}
