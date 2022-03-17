package com.shippingoo.Resource.job;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class GetJobResources {
    private Long id ;
    private Long buyerId;
    private String description;
    private String budget;
    
    private String catagoriesId;
    private String subCatagoriesId;
    private String typeId;

    
    private String city;
    private String country ;

    private String jobDuration;
    private LocalDateTime createdAt;

}