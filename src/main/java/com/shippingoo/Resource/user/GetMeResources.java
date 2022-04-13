package com.shippingoo.resource.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetMeResources {
    private String profilepicture;
    private String role;
    private String username;
    private String email;
    private String fullname;
    private String address;
    private String postalcode;
    private String phonenumber;
    private String city ;
    private String country;
    private String aboutme;
    private Long hangedMessage;
    private Long hangedNotification;
}
