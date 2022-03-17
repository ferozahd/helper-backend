package com.shippingoo.repository;

import java.util.List;
import java.util.Optional;

import com.shippingoo.Entity.JobTypes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobTypesRepository extends JpaRepository<JobTypes, Long> {

    List<JobTypes> findByCatagoriesIdAndCatagoriesStatusAndSubcatagoriesIdAndSubcatagoriesStatusAndStatus(
            Long catagoriesId, Boolean catagoriesStatus, Long subcatagoriesId, Boolean subcatagoiresStatus,
            Boolean status);

    Optional<JobTypes> findByIdAndCatagoriesStatusAndSubcatagoriesStatusAndStatus(long id, boolean catagroyStatus, boolean subCatagoryStatus, boolean Status);

}
