package com.shippingoo.Service;

import com.shippingoo.Resource.job.GetJobCatagoriesResources;
import com.shippingoo.Resource.job.GetJobSubCatagoriesResources;
import com.shippingoo.Resource.job.GetJobTypeResources;

import java.util.List;


public interface JobCatagoryService {

    List<GetJobCatagoriesResources> getJobCatagories();

    List<GetJobSubCatagoriesResources> getJobSubCatagories(Long catagoriesId);

    List<GetJobTypeResources> getJobTypes(Long catagoriesId, Long subcatagoriesId);
    
}
