package com.shippingoo.controller.Impl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Logger;

import com.shippingoo.Resource.Auth.PostApprovedToken;
import com.shippingoo.Resource.Auth.PostCreateUser;
import com.shippingoo.Resource.Auth.PostLogin;
import com.shippingoo.Resource.Auth.PostRegistrationResources;
import com.shippingoo.Service.AuthService;
import com.shippingoo.controller.AuthController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    Logger log = Logger.getLogger(AuthController.class.getName());
    private final AuthService authService;
    @Override
    public ResponseEntity<?> registration(PostRegistrationResources postRegistrationResources) {
        return authService.registration(postRegistrationResources);
    }


    @Override
    public ResponseEntity<?> approvedToken(PostApprovedToken postApprovedToken) {
        return authService.approvedToken(postApprovedToken);
    }


    @Override
    public ResponseEntity<?> createUser(PostCreateUser postCreateUser) {
        return authService.createUser(postCreateUser);
    }


    @Override
    public ResponseEntity<?> login(PostLogin postLogin) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return authService.login(postLogin);
    }




}
