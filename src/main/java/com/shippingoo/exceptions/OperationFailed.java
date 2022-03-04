package com.shippingoo.exceptions;

import com.shippingoo.Entity.Message;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class OperationFailed extends RuntimeException{
    public OperationFailed(String message){
        super(message);
    }
    public OperationFailed(String message, Throwable cause){
        super(message,cause);
    }
}
