package com.shippingoo.controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.shippingoo.resource.auth.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RequestMapping("/auth")
@CrossOrigin("*")
public interface AuthController {

   @PostMapping("/registration")
   public ResponseEntity<?> registration(@RequestBody PostRegistrationResources postRegistrationResources);

   @PostMapping("/approved")
   public ResponseEntity<?> approvedToken(@RequestBody PostApprovedToken postApprovedToken);
   
   @PostMapping("/createuser")
   public ResponseEntity<?> createUser(@RequestBody PostCreateUser postCreateUser);

   @PostMapping("/login")
   public ResponseEntity<?> login(@RequestBody PostLogin postLogin) throws InvalidKeySpecException, NoSuchAlgorithmException;
    
}
