package com.shippingoo.Entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class JobBid {
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  
    private Long id;
}
