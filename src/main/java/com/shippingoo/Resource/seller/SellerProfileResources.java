package com.shippingoo.resource.seller;

import com.shippingoo.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SellerProfileResources {

    private Long id;
    private String username;
    private String name;
    private String role;
    private String city;
    private String country;
    private String postcode;
    private String address;
    private boolean enabled;
    private String aboutme;
    private String photo;
    private String createdAt;
}
