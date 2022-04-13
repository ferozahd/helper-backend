package com.shippingoo.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
public class JobSelected{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    private Long buyerId;
    private String description;
    private String budget;
    // Catagories and SubCatagories will find by typeId
    private String typeId;

    
    private String city;
    private String country ;

    private Long sellerId;
    private LocalDateTime deadline;

    // private Graph tag;
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;

    @PrePersist
    private void onCreate(){
        this.createdAt=LocalDateTime.now();
    }
}
