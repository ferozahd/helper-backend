package com.shippingoo.controller;

import com.shippingoo.entity.Job;
import com.shippingoo.Resource.FeedbackMessage;
import com.shippingoo.resource.job.*;
import com.shippingoo.resource.jobBid.JobBidGetResource;
import com.shippingoo.resource.jobBid.JobBidPostResources;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/job")
@CrossOrigin("*")
public interface JobController {

    @GetMapping("/catagories")
    public ResponseEntity<List<GetJobCatagoriesResources>> getJobcatagories();

    @GetMapping("/{catagoriesid}/subcatagories")
    public ResponseEntity<List<GetJobSubCatagoriesResources>> getJobSubCatagories(@PathVariable("catagoriesid")String catagoriesId);

    @GetMapping("/{catagoriesId}/{subcatagoriesid}/type")
    public ResponseEntity<List<GetJobTypeResources>> getJobType(@PathVariable("catagoriesId")String catagoriesId, @PathVariable("subcatagoriesid") String subcatagoriesId);

    @PostMapping("/buyer/create")
    public ResponseEntity<Boolean> createJob(@RequestBody PostCreateJobResources postjob);

    @GetMapping("/buyer/pendingjob/{pagenumber}")
    public ResponseEntity<Page<Job>> getBuyerPendingjob(@PathVariable String pagenumber);

    @GetMapping("/seller/getavaragejob/{pagenumber}")
    public ResponseEntity<Page<Job>> getAvarageJob(@PathVariable String pagenumber);

    @PostMapping("/seller/apply/{jobId}")
    public ResponseEntity<FeedbackMessage> applyAJob(@PathVariable String jobId,@RequestBody JobBidPostResources jobBidPostResources );

    @GetMapping("/seller/getjob/{jobId}")
    public ResponseEntity<GetAJobForSeller> getSellerJobById(@PathVariable String jobId);

    @DeleteMapping("/buyer/deletejob/{jobId}")
    public ResponseEntity<Boolean> deleteJobById(@PathVariable String jobId);

    @GetMapping("/buyer/getapplicant/{jobId}")
    public ResponseEntity<List<JobBidGetResource>> getAllApplicant(@PathVariable String jobId);
}
