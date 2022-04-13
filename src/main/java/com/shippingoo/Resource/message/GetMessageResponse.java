package com.shippingoo.resource.message;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetMessageResponse {

    private Long userId;
    private String message;
    private LocalDateTime createdAt;

    public GetMessageResponse(Long id ,String message, LocalDateTime createdAt){
        this.userId=id;
        this.message=message;
        this.createdAt=createdAt;
    }

}
