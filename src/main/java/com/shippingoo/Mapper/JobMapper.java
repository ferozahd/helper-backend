package com.shippingoo.mapper;

import com.shippingoo.resource.job.*;
import com.shippingoo.resource.jobBid.JobBidGetResource;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.ArrayList;
import java.util.List;

import com.shippingoo.entity.Job;
import com.shippingoo.entity.JobBid;
import com.shippingoo.entity.JobCatagories;
import com.shippingoo.entity.JobSubCatagories;
import com.shippingoo.entity.JobTypes;
import com.shippingoo.resource.jobBid.JobBidPostResources;
import com.shippingoo.exceptions.InvalidValue;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@Named("JobMapper")
public interface JobMapper {

    default List<GetJobTypeResources> getTypeResource(List<JobTypes> jobtypes) {
        if (jobtypes == null || jobtypes.size() == 0) {
            return null;
        }
        List<GetJobTypeResources> jobtypeList = new ArrayList<GetJobTypeResources>();
        for (int i = 0; i < jobtypes.size(); i++) {

            JobTypes jobtype = jobtypes.get(i);
            GetJobTypeResources jobTypeResources = new GetJobTypeResources();

            jobTypeResources.setId(jobtype.getId());
            jobTypeResources.setCatagoriesid(jobtype.getCatagoriesId());
            jobTypeResources.setSubcatagoriesid(jobtype.getSubcatagoriesId());
            jobTypeResources.setType(jobtype.getType());

            jobtypeList.add(jobTypeResources);
        }
        return jobtypeList;
    }

    default List<GetJobSubCatagoriesResources> getJobSubCatagoriesResource(List<JobSubCatagories> subcata) {
        if (subcata == null || subcata.size() == 0) {
            return null;
        }
        List<GetJobSubCatagoriesResources> jobSubcatagoriesList = new ArrayList<GetJobSubCatagoriesResources>();
        for (int i = 0; i < subcata.size(); i++) {

            JobSubCatagories subCatagories = subcata.get(i);
            GetJobSubCatagoriesResources jobSubCatagoriesResources = new GetJobSubCatagoriesResources();

            jobSubCatagoriesResources.setId(subCatagories.getId());
            jobSubCatagoriesResources.setCatagoriesid(subCatagories.getCatagoriesid());
            jobSubCatagoriesResources.setSubcatagories(subCatagories.getSubcatagoriesname());

            jobSubcatagoriesList.add(jobSubCatagoriesResources);
        }
        return jobSubcatagoriesList;
    }

    default List<GetJobCatagoriesResources> getJobCatagoriesResource(List<JobCatagories> jobcatagories) {
        if (jobcatagories == null || jobcatagories.size() == 0) {
            return null;
        }
        List<GetJobCatagoriesResources> jobcatagorieslist = new ArrayList<GetJobCatagoriesResources>();
        for (int i = 0; i < jobcatagories.size(); i++) {

            JobCatagories catagory = jobcatagories.get(i);
            GetJobCatagoriesResources jobCatagoriesResources = new GetJobCatagoriesResources();

            jobCatagoriesResources.setId(catagory.getId());
            jobCatagoriesResources.setCatagories(catagory.getName());

            jobcatagorieslist.add(jobCatagoriesResources);
        }
        return jobcatagorieslist;
    }

    default Job jobFromPostCreateJob(PostCreateJobResources postjob, JobTypes jobType) {
        if (postjob == null || jobType == null) {
            return null;
        }
        try {
            Job job = new Job();
            job.setBudget(postjob.getBudget());
            job.setDescription(postjob.getDescription());
            job.setTypeId(jobType.getId());
            job.setTypeName(jobType.getType());
            job.setCatagoriesId(jobType.getCatagoriesId());
            job.setSubCatagoriesId(jobType.getSubcatagoriesId());
            job.setCity(postjob.getCity());
            job.setCountry(postjob.getCountry());
            job.setJobDuration(postjob.getJobDuration());
            job.setApplied(0l);
            return job;
        } catch (Exception e) {
            throw new InvalidValue(e.getLocalizedMessage());
        }

    }

    default List<GetBuyerPendingJobResource> getBuyerpendingResource(List<Job> jobs) {
        if (jobs.size() < 0) {
            return null;
        }
        List<GetBuyerPendingJobResource> bprList = new ArrayList<GetBuyerPendingJobResource>();
        for (int i = 0; i < jobs.size(); i++) {
            Job job = jobs.get(i);
            GetBuyerPendingJobResource bpr = new GetBuyerPendingJobResource();
            bpr.setId(job.getId());
            bpr.setDescription(job.getDescription());
            bpr.setBudget(job.getBudget());
            bpr.setTypeId(job.getTypeId());
            bpr.setTypename(job.getTypeName());
            bpr.setCity(job.getCity());
            bpr.setCountry(job.getCountry());
            bpr.setApplicantNumber(job.getApplied());
            bpr.setJobDuration(job.getJobDuration());
            bpr.setCreatedAt(job.getCreatedAt());
            bprList.add(bpr);
        }
        return bprList;
    }

    @Named("jobBidPostResourcesToJobBid")
    default JobBid jobBidPostResourcesToJobBid(JobBidPostResources jobBidPostResources) {
        if(jobBidPostResources == null){
            return  null;
        }
        JobBid jobBid=new JobBid();
        jobBid.setSellerBudget(jobBidPostResources.getSellerBudget());
        jobBid.setSellerLetter(jobBidPostResources.getSellerLetter());
        jobBid.setSellerDeadline(jobBidPostResources.getSellerDeadline());

        return  jobBid;
    }

    @Named("jobToGetAJobForSeller")
    default GetAJobForSeller jobToGetAJobForSeller(Job job){
        if(job==null){
            return  null;
        }
        GetAJobForSeller gjs=new GetAJobForSeller();
        gjs.setId(job.getId());
        gjs.setBuyerId(job.getBuyerId());
        gjs.setDescription(job.getDescription());
        gjs.setBudget(job.getBudget());
        gjs.setCatagoriesId(job.getCatagoriesId());
        gjs.setSubCatagoriesId(job.getSubCatagoriesId());
        gjs.setTypeId(job.getTypeId());
        gjs.setTypeName(job.getTypeName());
        gjs.setCity(job.getCity());
        gjs.setCountry(job.getCountry());
        gjs.setApplied(job.getApplied());
        gjs.setJobDuration(job.getJobDuration());
        gjs.setCreatedAt(job.getCreatedAt());
        return  gjs;
    }

    @Named("jobBidToJobBidGetResource")
    default JobBidGetResource jobBidToJobBidGetResource(JobBid jobBid){
        if(jobBid==null){
            return  null;
        }

        JobBidGetResource jbgr=new JobBidGetResource();
        jbgr.setId(jobBid.getId());
        jbgr.setJobId(jobBid.getJobId());
        jbgr.setSellerId(jobBid.getSellerId());
        jbgr.setSellerBudget(jobBid.getSellerBudget());
        jbgr.setSellerDeadline(jobBid.getSellerDeadline());
        jbgr.setSellerLetter(jobBid.getSellerLetter());
        jbgr.setCreatedAt(jobBid.getCreatedAt());
        return  jbgr;
    }
}