package com.shippingoo.Resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackMessage {

    private String message;

    public FeedbackMessage(String message) {
        this.message = message;
    }
}
