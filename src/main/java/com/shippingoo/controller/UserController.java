package com.shippingoo.controller;

import java.security.Principal;

import com.shippingoo.Resource.user.GetMeResources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@CrossOrigin("*")
public interface UserController {


    @PostMapping("/getme")
    public ResponseEntity<GetMeResources> getMe();

    @GetMapping("/getbuddylist")
    public ResponseEntity<?> getBuddyList();

    @GetMapping("/getPendinglist")
    public ResponseEntity<?> getPendingList();

    
    
}
