package com.shippingoo.Service.impl;

import java.util.List;
import java.util.Optional;

import com.shippingoo.Entity.Job;
import com.shippingoo.Entity.JobTypes;
import com.shippingoo.Mapper.JobMapper;
import com.shippingoo.Resource.job.GetBuyerPendingJobResource;
import com.shippingoo.Resource.job.PostCreateJobResources;
import com.shippingoo.Service.CommonService;
import com.shippingoo.Service.JobService;
import com.shippingoo.exceptions.OperationFailed;
import com.shippingoo.repository.JobRepository;
import com.shippingoo.repository.JobTypesRepository;

import org.jboss.logging.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private Logger log = Logger.getLogger(JobServiceImpl.class);
    private final JobMapper jobMapper = Mappers.getMapper(JobMapper.class);
    private final JobRepository jobRepository;
    private final CommonService commonService;
    private final JobTypesRepository jobTypesRepository;

    @Override
    public Boolean createJob(PostCreateJobResources postjob) {
        try {
            Long buyerId = commonService.getUserIdFromContext();

            Optional<JobTypes> jobtypes = jobTypesRepository.findByIdAndCatagoriesStatusAndSubcatagoriesStatusAndStatus(
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
    public List<GetBuyerPendingJobResource> getBuyerPendingjob() {
        try {
            Long userId = commonService.getUserIdFromContext();
            List<Job> jobs = jobRepository.findByBuyerId(userId);
            return jobMapper.getBuyerpendingResource(jobs);
        } catch (Exception e) {
            throw new OperationFailed(e.getLocalizedMessage());
        }
    }

}
