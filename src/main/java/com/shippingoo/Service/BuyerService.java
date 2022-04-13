package com.shippingoo.service;

import com.shippingoo.resource.seller.SellerProfileResources;
import org.springframework.http.ResponseEntity;

public interface BuyerService {
    ResponseEntity<SellerProfileResources> getSellerProfile(Long prfileId);
}
