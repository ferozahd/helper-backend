package com.shippingoo.Resource.Auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthRegistrationErrorResponse {
    private String usertype;
    private String email;
    private String name;

}
