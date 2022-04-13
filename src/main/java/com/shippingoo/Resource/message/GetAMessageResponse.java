package com.shippingoo.resource.message;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetAMessageResponse {
    private Long messageId;
    private String messageBody;
    private String deliverytime;
}
