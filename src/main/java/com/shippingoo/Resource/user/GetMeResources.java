package com.shippingoo.Resource.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetMeResources {
    private String profilepicture;
    private String role;
    private String username;
    private String fullname;
    private Long hangedMessage;
    private Long hangedNotification;
}
