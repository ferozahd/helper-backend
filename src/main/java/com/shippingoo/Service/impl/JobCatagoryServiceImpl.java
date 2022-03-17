package com.shippingoo.Service.impl;

import com.shippingoo.Entity.JobCatagories;
import com.shippingoo.Entity.JobSubCatagories;
import com.shippingoo.Entity.JobTypes;
import com.shippingoo.Mapper.JobMapper;
import com.shippingoo.Resource.job.GetJobCatagoriesResources;
import com.shippingoo.Resource.job.GetJobSubCatagoriesResources;
import com.shippingoo.Resource.job.GetJobTypeResources;
import com.shippingoo.Service.JobCatagoryService;
import com.shippingoo.exceptions.OperationFailed;
import com.shippingoo.repository.JobCatagoriesRepository;
import com.shippingoo.repository.JobSubCatagoriesRepository;
import com.shippingoo.repository.JobTypesRepository;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobCatagoryServiceImpl implements JobCatagoryService {


    
    private final JobCatagoriesRepository jobCatagoriesRepository;
    private final JobSubCatagoriesRepository jobSubCatagoriesRepository;
    private final JobTypesRepository jobTypesRepository;
    private final JobMapper jobMapper = Mappers.getMapper(JobMapper.class);

    @Override
    public List<GetJobCatagoriesResources> getJobCatagories() {
        try {
            List<JobCatagories> jobcatagories = jobCatagoriesRepository.findByStatus(true);
            return jobMapper.getJobCatagoriesResource(jobcatagories);
        } catch (Exception e) {
            throw new OperationFailed(e.getLocalizedMessage());
        }

    }

    @Override
    public List<GetJobSubCatagoriesResources> getJobSubCatagories(Long catagoriesId) {
        try {
    
            List<JobSubCatagories> subcata = jobSubCatagoriesRepository
                    .findByCatagoriesidAndCatagoriesStatusAndStatus(catagoriesId, true, true);

            return jobMapper.getJobSubCatagoriesResource(subcata);
        } catch (Exception e) {
            throw new OperationFailed(e.getLocalizedMessage());
        }

    }

    @Override
    public List<GetJobTypeResources> getJobTypes(Long catagoriesId, Long subcatagoriesId) {
        try {
            List<JobTypes> jobtypes = jobTypesRepository
                    .findByCatagoriesIdAndCatagoriesStatusAndSubcatagoriesIdAndSubcatagoriesStatusAndStatus(
                            catagoriesId, true, subcatagoriesId, true, true);
            return jobMapper.getTypeResource(jobtypes);
        } catch (Exception e) {
            throw new OperationFailed(e.getLocalizedMessage());
        }
    }

}
