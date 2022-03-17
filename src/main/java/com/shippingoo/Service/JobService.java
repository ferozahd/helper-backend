package com.shippingoo.Service;

import java.util.List;

import com.shippingoo.Resource.job.GetBuyerPendingJobResource;
import com.shippingoo.Resource.job.PostCreateJobResources;

public interface JobService {

    Boolean createJob(PostCreateJobResources postjob);

    public List<GetBuyerPendingJobResource> getBuyerPendingjob();
    
}
