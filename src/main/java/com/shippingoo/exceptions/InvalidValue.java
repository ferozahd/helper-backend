package com.shippingoo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidValue extends RuntimeException {
    public InvalidValue(String message){
        super(message);
    }
    
	public InvalidValue(String message, Throwable cause) {
		super(message, cause);
	}
}
