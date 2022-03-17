package com.shippingoo.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class JobTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long catagoriesId;
    private Long subcatagoriesId;

    private String type;
    // true means active

    private Boolean catagoriesStatus;
    private Boolean subcatagoriesStatus;
    private Boolean status;
}
