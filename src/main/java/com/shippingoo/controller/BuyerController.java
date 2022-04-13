package com.shippingoo.controller;

import com.shippingoo.resource.seller.SellerProfileResources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/buyer")
@CrossOrigin("*")
public interface BuyerController {
    @GetMapping("/seller/profile/{profileId}")
    public ResponseEntity<SellerProfileResources> getSellerProfile(@PathVariable String profileId);
}
