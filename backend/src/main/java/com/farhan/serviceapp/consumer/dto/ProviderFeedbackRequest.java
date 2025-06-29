// 📁 com.farhan.serviceapp.dto.ProviderFeedbackRequest.java
package com.farhan.serviceapp.consumer.dto;

import lombok.Data;

@Data
public class ProviderFeedbackRequest {
    private String comment;
    private int rating;
    private boolean liked;
}
