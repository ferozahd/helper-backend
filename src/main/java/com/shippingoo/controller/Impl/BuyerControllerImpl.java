package com.shippingoo.controller.Impl;

import com.shippingoo.controller.BuyerController;
import com.shippingoo.exceptions.OperationFailed;
import com.shippingoo.resource.seller.SellerProfileResources;
import com.shippingoo.service.BuyerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BuyerControllerImpl implements BuyerController {

    private final BuyerService buyerService;

    @Override
    public ResponseEntity<SellerProfileResources> getSellerProfile(String profileId) {
        try {
         return buyerService.getSellerProfile(Long.parseLong(profileId));
        } catch (Exception e) {
            throw new OperationFailed("Invalid Id"+e.getLocalizedMessage());
        }

    }
}
