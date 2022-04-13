package com.shippingoo.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.shippingoo.entity.JobBid;

import com.shippingoo.resource.jobBid.JobBidGetResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JobBidRepository extends JpaRepository<JobBid , Long>{

    Optional<JobBid> findBySellerIdAndJobId(Long userIdFromContext, Long jobId);

    Optional<JobBid> findByidAndSellerId(Long jobId, Long userIdFromContext);

    List<JobBid> findByJobId(Long jobId);


    @Query(value = "select new com.shippingoo.resource.jobBid.JobBidGetResource(j.id,j.jobId,j.sellerId,j.sellerBudget,j.sellerDeadline,j.sellerLetter,j.createdAt,u.name,u.photo) from JobBid as j LEFT JOIN User as u on u.id =j.sellerId WHERE j.jobId= :jobId",nativeQuery = false)
    List<JobBidGetResource> getAppliedJob(@Param("jobId") Long jobId);
}
