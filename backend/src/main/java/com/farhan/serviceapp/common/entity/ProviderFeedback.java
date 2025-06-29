// 📁 src/main/java/com/farhan/serviceapp/common/entity/ProviderFeedback.java
package com.farhan.serviceapp.common.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProviderFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User consumer;

    @ManyToOne(optional = false)
    private User provider;

    @Column(length = 1000)
    private String comment;

    @Column(nullable = false)
    private int rating;

    private boolean liked;

    private LocalDateTime createdAt;
}
