package com.shippingoo.repository;

import java.util.List;

import com.shippingoo.Entity.Job;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long>{

    List<Job> findByBuyerId(Long userId);
    
}
