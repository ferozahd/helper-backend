package com.shippingoo.service;

import com.shippingoo.entity.Job;
import com.shippingoo.Resource.FeedbackMessage;
import com.shippingoo.resource.job.GetAJobForSeller;
import com.shippingoo.resource.job.GetJobResources;
import com.shippingoo.resource.job.PostCreateJobResources;
import com.shippingoo.resource.jobBid.JobBidGetResource;
import com.shippingoo.resource.jobBid.JobBidPostResources;

import org.springframework.data.domain.Page;

import java.util.List;

public interface JobService {

    Boolean createJob(PostCreateJobResources postjob);

    public Page<Job> getBuyerPendingjob(int page);

    public Page<Job> getAvarageJob(int page);

    FeedbackMessage applyToJob(Long jobId, JobBidPostResources jobBidPostResources);

    GetAJobForSeller getSellerJobById(Long jobId);

    List<JobBidGetResource> getAllApplicant(Long jobId);
}
