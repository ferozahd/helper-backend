package com.shippingoo.resource.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PostCreateUser {
    private String token;
    private String name;
    private String password;
}
