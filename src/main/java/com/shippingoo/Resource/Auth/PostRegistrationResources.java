package com.shippingoo.Resource.Auth;

import com.shippingoo.enums.auth.TypeUser;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostRegistrationResources {
 private String usertype;
 private String email;
 private String username;   
}
