package com.shippingoo.controller.Impl;

import com.shippingoo.entity.Job;
import com.shippingoo.Resource.FeedbackMessage;
import com.shippingoo.resource.job.*;
import com.shippingoo.resource.jobBid.JobBidGetResource;
import com.shippingoo.resource.jobBid.JobBidPostResources;
import com.shippingoo.service.JobCatagoryService;
import com.shippingoo.service.JobService;
import com.shippingoo.controller.JobController;
import com.shippingoo.exceptions.InvalidValue;
import com.shippingoo.exceptions.OperationFailed;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class JobControllerImpl implements JobController {
  private Logger log = Logger.getLogger(JobControllerImpl.class.getName());
  private final JobCatagoryService jobcataServie;
  private final JobService jobService;

  @Override
  public ResponseEntity<List<GetJobCatagoriesResources>> getJobcatagories() {
    return ResponseEntity.ok(jobcataServie.getJobCatagories());
  }

  @Override
  public ResponseEntity<List<GetJobSubCatagoriesResources>> getJobSubCatagories(
      String catagoriesId) {
    try {
      Long cataId = Long.parseLong(catagoriesId);
      return ResponseEntity.ok(jobcataServie.getJobSubCatagories(cataId));

    } catch (Exception e) {
      throw new OperationFailed(e.getLocalizedMessage());
    }
  }

  @Override
  public ResponseEntity<List<GetJobTypeResources>> getJobType(
      String catagoriesId, String subcatagoriesId) {
    try {
      return ResponseEntity.ok(
          jobcataServie.getJobTypes(Long.parseLong(catagoriesId), Long.parseLong(subcatagoriesId)));
    } catch (Exception e) {
      throw new OperationFailed("user valid url");
    }
  }

  @Override
  public ResponseEntity<Boolean> createJob(PostCreateJobResources postjob) {
    return ResponseEntity.ok(jobService.createJob(postjob));
  }

  @Override
  public ResponseEntity<Page<Job>> getBuyerPendingjob(String pagenumber) {
    try {
      int page = Integer.parseInt(pagenumber);
      return ResponseEntity.ok(jobService.getBuyerPendingjob(page));
    } catch (Exception e) {
      throw new InvalidValue(e.getMessage());
    }
  }

  @Override
  public ResponseEntity<Page<Job>> getAvarageJob(String pagenumber) {
    try {
      int page = Integer.parseInt(pagenumber);
      return ResponseEntity.ok(jobService.getAvarageJob(page));
    } catch (Exception e) {
      throw new InvalidValue(e.getMessage());
    }
  }

  @Override
  public ResponseEntity<FeedbackMessage> applyAJob(
      String jobId, JobBidPostResources jobBidPostResources) {
    try {

      Long jobIdentity = Long.parseLong(jobId);

      return ResponseEntity.ok(jobService.applyToJob(jobIdentity, jobBidPostResources));
    } catch (Exception e) {
      throw new InvalidValue(e.getMessage());
    }
  }

  @Override
  public ResponseEntity<GetAJobForSeller> getSellerJobById(String jobId) {
    try {
      Long id = Long.parseLong(jobId);
      return ResponseEntity.ok(jobService.getSellerJobById(id));
    } catch (Exception e) {
      throw new InvalidValue(e.getMessage());
    }
  }

  @Override
  public ResponseEntity<Boolean> deleteJobById(String jobId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ResponseEntity<List<JobBidGetResource>> getAllApplicant(String jobId) {
    try {
      Long jId = Long.parseLong(jobId);
      return ResponseEntity.ok(jobService.getAllApplicant(jId));

    } catch (Exception e) {
      throw new OperationFailed("Invalid job id"+e.getLocalizedMessage());
    }
  }
}
