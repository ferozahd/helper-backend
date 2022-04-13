package com.shippingoo.resource.job;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class GetBuyerPendingJobResource{
    private Long id;
    private String description;
    private String budget;
    private String  typename;
    private Long typeId;
    private String city;
    private String country;
    private Long applicantNumber;
    private String jobDuration;
    private LocalDateTime createdAt;
}
