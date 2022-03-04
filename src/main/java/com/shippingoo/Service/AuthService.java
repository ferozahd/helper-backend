package com.shippingoo.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.shippingoo.Resource.FeedbackMessage;
import com.shippingoo.Resource.Auth.PostApprovedToken;
import com.shippingoo.Resource.Auth.PostCreateUser;
import com.shippingoo.Resource.Auth.PostLogin;
import com.shippingoo.Resource.Auth.PostRegistrationResources;

import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<FeedbackMessage> registration(PostRegistrationResources postRegistrationResources);

    ResponseEntity<?> approvedToken(PostApprovedToken postApprovedToken);

    ResponseEntity<?> createUser(PostCreateUser postCreateUser);

    ResponseEntity<?> login(PostLogin postLogin) throws InvalidKeySpecException, NoSuchAlgorithmException;
    
}
