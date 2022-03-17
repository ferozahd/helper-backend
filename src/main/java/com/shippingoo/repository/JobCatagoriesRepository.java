package com.shippingoo.repository;

import java.util.List;

import com.shippingoo.Entity.JobCatagories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobCatagoriesRepository extends JpaRepository<JobCatagories,Long>{

    // @Query("SELECT u FROM JobCatagories u WHERE u.status = true")
    // List<GetJobCatagories> findAllStatusTrue();

    List<JobCatagories> findByStatus(boolean b);
    
}
