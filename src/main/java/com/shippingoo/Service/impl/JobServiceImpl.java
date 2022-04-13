package com.shippingoo.service.impl;

import com.shippingoo.entity.Job;
import com.shippingoo.entity.JobBid;
import com.shippingoo.entity.JobTypes;
import com.shippingoo.mapper.JobMapper;
import com.shippingoo.Resource.FeedbackMessage;
import com.shippingoo.resource.job.GetAJobForSeller;
import com.shippingoo.resource.job.PostCreateJobResources;
import com.shippingoo.resource.jobBid.JobBidGetResource;
import com.shippingoo.resource.jobBid.JobBidPostResources;
import com.shippingoo.service.CommonService;
import com.shippingoo.service.JobService;
import com.shippingoo.exceptions.OperationFailed;
import com.shippingoo.repository.JobBidRepository;
import com.shippingoo.repository.JobRepository;
import com.shippingoo.repository.JobTypesRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.TransactionScoped;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
  private Logger log = Logger.getLogger(JobServiceImpl.class.getName());
  private final JobMapper jobMapper = Mappers.getMapper(JobMapper.class);
  private final JobRepository jobRepository;
  private final CommonService commonService;
  private final JobTypesRepository jobTypesRepository;
  private final JobBidRepository jobBidRepository;

  @Override
  public Boolean createJob(PostCreateJobResources postjob) {
    try {
      Long buyerId = commonService.getUserIdFromContext();

      Optional<JobTypes> jobtypes =
          jobTypesRepository.findByIdAndCatagoriesStatusAndSubcatagoriesStatusAndStatus(
              Long.parseLong(postjob.getTypeId()), true, true, true);
      if (jobtypes.isPresent()) {
        Job job = jobMapper.jobFromPostCreateJob(postjob, jobtypes.get());
        job.setBuyerId(buyerId);
        job = jobRepository.save(job);
        return true;
      } else {
        throw new OperationFailed("job type error");
      }
    } catch (Exception e) {
      throw new OperationFailed(e.getLocalizedMessage());
    }
  }

  @Override
  public Page<Job> getBuyerPendingjob(int page) {
    try {
      Pageable pageable = PageRequest.of(page, 5);
      Page<Job> job = jobRepository.findByBuyerId(commonService.getUserIdFromContext(), pageable);
      return job;
    } catch (Exception e) {
      throw new OperationFailed(e.getLocalizedMessage());
    }
  }

  @Override
  public Page<Job> getAvarageJob(int page) {
    try {
      Pageable pageable = PageRequest.of(page, 5);
      Page<Job> job = jobRepository.findAll(pageable);
      //            List<Job> jobs=job.getContent();
      //            jobs.forEach(val->val.setBuyerId(null));
      //            Collection<Job> jobc=jobs;
      //            job.getContent().clear();
      //            job.getContent().addAll(jobc);
      return job;
    } catch (Exception e) {
      throw new OperationFailed(e.getLocalizedMessage());
    }
  }

  @Override
  @TransactionScoped
  public FeedbackMessage applyToJob(Long jobId, JobBidPostResources jobBidPostResources) {
    Optional<Job> job = jobRepository.findById(jobId);
    if (job.isPresent()) {
      Optional<JobBid> isApply =
          jobBidRepository.findBySellerIdAndJobId(commonService.getUserIdFromContext(), jobId);
      if (isApply.isPresent()) {
        throw new OperationFailed("You allready apply to this job");
      } else {
        Job existedJob = job.get();
        existedJob.setApplied(existedJob.getApplied() + 1);

        JobBid jobBid = jobMapper.jobBidPostResourcesToJobBid(jobBidPostResources);
        jobBid.setJobId(jobId);
        jobBid.setSellerId(commonService.getUserIdFromContext());
        jobBidRepository.save(jobBid);
        jobRepository.save(existedJob);
        return new FeedbackMessage("Successfully apply");
      }
    } else {
      throw new OperationFailed("Job not found ");
    }
  }

  @Override
  public GetAJobForSeller getSellerJobById(Long jobId) {
    Optional<Job> isJob = jobRepository.findById(jobId);
    if (isJob.isPresent()) {
      Optional<JobBid> isApply =
          jobBidRepository.findBySellerIdAndJobId(commonService.getUserIdFromContext(), jobId);
      GetAJobForSeller jobResponse = jobMapper.jobToGetAJobForSeller(isJob.get());

      if (isApply.isPresent()) {
        jobResponse.setApplyStatus(1);
      } else {
        jobResponse.setApplyStatus(0);
      }

      return jobResponse;
    } else {
      throw new OperationFailed("This job is not found");
    }
  }

  @Override
  public List<JobBidGetResource> getAllApplicant(Long jobId) {
    boolean job = jobRepository.existsByIdAndBuyerId(jobId, commonService.getUserIdFromContext());
    if (job) {
      List<JobBidGetResource> jobBids = jobBidRepository.getAppliedJob(jobId);
      if (jobBids.size() > 0) {
        jobBids.forEach(item->{
          if(item.getSellerProfilePicture()==null){
            item.setSellerProfilePicture("http://localhost:5000/file/get/default.jpg");
          }
        });
        return jobBids;
      } else {
        return new ArrayList<JobBidGetResource>();
      }
    } else {
      throw new OperationFailed("No job found ");
    }
  }
}
