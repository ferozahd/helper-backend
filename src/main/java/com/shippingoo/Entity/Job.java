package com.shippingoo.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
public class Job {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    private Long buyerId;

    @Column(length = 1000)
    private String description;
    private String budget;
    
    private Long catagoriesId;
    private Long subCatagoriesId;
    private Long typeId;

    // private String catagories;
    // private String subCatagories;
    private String typeName;
    
    private String city;
    private String country ;
    private Long applied;
    private String jobDuration;
    // private Graph tag;
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;
    
    
    @PrePersist
    private void onCreate(){
        this.applied=0l;
        this.createdAt=LocalDateTime.now();
    }
}
