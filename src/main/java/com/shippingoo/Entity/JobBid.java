package com.shippingoo.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import org.mapstruct.Mapping;

@Entity
@Data
public class JobBid {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Long jobId;
  @Column(insertable = false,updatable = false)
  private Long sellerId;

  private Long sellerBudget;
  private int sellerDeadline;
  private String sellerLetter;

  private LocalDateTime createdAt;

  @PrePersist
  private void onCreate() {
    createdAt = LocalDateTime.now();
  }
}
