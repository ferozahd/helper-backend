package com.shippingoo.Resource.user;


import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class PatchUserResources {
    private String aboutme;
    private String city;
    private String address;
    private String phonenumber;
    private String country;
    private String fullname;
    private String postalcode;
}
