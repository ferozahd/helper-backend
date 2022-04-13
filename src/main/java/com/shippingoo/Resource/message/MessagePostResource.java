package com.shippingoo.resource.message;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Getter
@Setter
public class MessagePostResource {
    @NotBlank
    @NotNull
    private String messageBody;
}
