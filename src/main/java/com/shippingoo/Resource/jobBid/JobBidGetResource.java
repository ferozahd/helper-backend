package com.shippingoo.resource.jobBid;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class JobBidGetResource {

  private Long id;

  private Long jobId;
  private Long sellerId;

  private Long sellerBudget;
  private String sellerLetter;

  private LocalDateTime createdAt;
  private int sellerDeadline;

  private String sellername;
  private String sellerProfilePicture;

  public JobBidGetResource() {}


  public JobBidGetResource(
      long id,
      long jobId,
      long sellerId,
      long sellerBudget,
      int sellerDeadline,
      String sellerLetter,
      LocalDateTime createdAt,
      String sellername,
      String sellerProfilePicture) {
    this.id = id;
    this.jobId = jobId;
    this.sellerId = sellerId;
    this.sellerLetter = sellerLetter;
    this.sellerBudget = sellerBudget;
    this.sellerDeadline = sellerDeadline;
    this.createdAt = createdAt;
    this.sellername = sellername;
    this.sellerProfilePicture = sellerProfilePicture;
  }
}
