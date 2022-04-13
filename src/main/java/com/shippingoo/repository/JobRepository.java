package com.shippingoo.repository;

import java.util.List;

import com.shippingoo.entity.Job;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long>{

    List<Job> findByBuyerId(Long userId);

    Page<Job> findByBuyerId(Long userId, Pageable pageable);

    boolean existsByIdAndBuyerId(Long jobId, Long userIdFromContext);
}
