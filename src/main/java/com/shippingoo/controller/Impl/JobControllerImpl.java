package com.shippingoo.controller.Impl;

import com.shippingoo.Resource.job.*;
import com.shippingoo.Service.JobCatagoryService;
import com.shippingoo.Service.JobService;
import com.shippingoo.controller.JobController;
import com.shippingoo.exceptions.OperationFailed;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class JobControllerImpl implements JobController {

    private final JobCatagoryService jobcataServie;
    private final JobService jobService;


    @Override
    public ResponseEntity<List<GetJobCatagoriesResources>> getJobcatagories() {
        return ResponseEntity.ok(jobcataServie.getJobCatagories());
    }




    @Override
    public ResponseEntity<List<GetJobSubCatagoriesResources>> getJobSubCatagories(String catagoriesId) {
        try {
            Long cataId = Long.parseLong(catagoriesId);
            return ResponseEntity.ok(jobcataServie.getJobSubCatagories(cataId));

        } catch (Exception e) {
            throw new OperationFailed(e.getLocalizedMessage());
        }
    }



    @Override
    public ResponseEntity<List<GetJobTypeResources>> getJobType(String catagoriesId, String subcatagoriesId) {
        try {
            return ResponseEntity
                    .ok(jobcataServie.getJobTypes(Long.parseLong(catagoriesId),Long.parseLong(subcatagoriesId)));
        } catch (Exception e) {
            throw new OperationFailed("user valid url");
        }
    }



    @Override
    public ResponseEntity<Boolean> createJob(PostCreateJobResources postjob) {
        return ResponseEntity.ok(jobService.createJob(postjob));
    }




    @Override
    public ResponseEntity<List<GetBuyerPendingJobResource>> getBuyerPendingjob() {
        return ResponseEntity.ok(jobService.getBuyerPendingjob());
    }



   

}
