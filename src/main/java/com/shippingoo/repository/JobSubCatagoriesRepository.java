package com.shippingoo.repository;

import java.util.List;

import com.shippingoo.Entity.JobSubCatagories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobSubCatagoriesRepository extends JpaRepository<JobSubCatagories,Long>{

    List<JobSubCatagories> findByCatagoriesidAndCatagoriesStatusAndStatus(Long catagoriesId,Boolean catagoriesStatus,Boolean status);
    
}