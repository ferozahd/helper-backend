package com.shippingoo.controller;

import com.shippingoo.Resource.job.GetJobCatagoriesResources;
import com.shippingoo.Resource.job.GetJobSubCatagoriesResources;
import com.shippingoo.Resource.job.GetJobTypeResources;
import com.shippingoo.Resource.job.PostCreateJobResources;
import com.shippingoo.Resource.job.GetBuyerPendingJobResource;

import java.util.List;

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

    @GetMapping("/buyer/pendingjob")
    public ResponseEntity<List<GetBuyerPendingJobResource>> getBuyerPendingjob();

}
