package com.shippingoo.resource.job;

import lombok.Data;

@Data
public class GetJobTypeResources {
    private Long id;
    private Long catagoriesid;
    private Long subcatagoriesid;
    private String type;
}
