package com.shippingoo.resource.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginFeedback {
    private String token;

    public LoginFeedback(String token){
        this.token=token;

    }
}
