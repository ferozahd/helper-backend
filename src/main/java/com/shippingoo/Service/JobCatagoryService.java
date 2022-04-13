package com.shippingoo.service;

import com.shippingoo.resource.job.GetJobCatagoriesResources;
import com.shippingoo.resource.job.GetJobSubCatagoriesResources;
import com.shippingoo.resource.job.GetJobTypeResources;

import java.util.List;


public interface JobCatagoryService {

    List<GetJobCatagoriesResources> getJobCatagories();

    List<GetJobSubCatagoriesResources> getJobSubCatagories(Long catagoriesId);

    List<GetJobTypeResources> getJobTypes(Long catagoriesId, Long subcatagoriesId);
    
}
