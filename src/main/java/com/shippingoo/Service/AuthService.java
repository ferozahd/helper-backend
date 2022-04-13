package com.shippingoo.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.shippingoo.Resource.FeedbackMessage;
import com.shippingoo.resource.auth.*;


import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<FeedbackMessage> registration(PostRegistrationResources postRegistrationResources);

    ResponseEntity<?> approvedToken(PostApprovedToken postApprovedToken);

    ResponseEntity<?> createUser(PostCreateUser postCreateUser);

    ResponseEntity<?> login(PostLogin postLogin) throws InvalidKeySpecException, NoSuchAlgorithmException;
    
}
