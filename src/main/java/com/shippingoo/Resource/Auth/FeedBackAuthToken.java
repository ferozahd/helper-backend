package com.shippingoo.resource.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FeedBackAuthToken {
    private String token;
    public FeedBackAuthToken(String token){
        this.token=token;
    }

}
