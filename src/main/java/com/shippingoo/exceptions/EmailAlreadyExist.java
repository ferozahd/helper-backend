package com.shippingoo.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailAlreadyExist extends RuntimeException {
	public EmailAlreadyExist(String message) {
		super(message);
	}

	public EmailAlreadyExist(String message, Throwable cause) {
		super(message, cause);
	}
}
