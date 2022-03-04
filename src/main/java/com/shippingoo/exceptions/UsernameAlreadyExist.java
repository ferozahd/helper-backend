package com.shippingoo.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameAlreadyExist extends RuntimeException {
	public UsernameAlreadyExist(String message) {
		super(message);
	}

	public UsernameAlreadyExist(String message, Throwable cause) {
		super(message, cause);
	}
}
