package com.shippingoo.Resource.job;


import lombok.Data;

@Data
public class PostCreateJobResources {

    private Long buyerId;
    private String description;
    private String budget;
    private String typeId;
    private String city;
    private String country;
    private String jobDuration;

}