package com.shippingoo.controller;


import com.github.javafaker.Bool;
import com.shippingoo.Resource.user.GetMeResources;
import com.shippingoo.Resource.user.PatchUserResources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/user")
@CrossOrigin("*")
public interface UserController {


    @GetMapping("/getme")
    public ResponseEntity<GetMeResources> getMe();

    @GetMapping("/getbuddylist")
    public ResponseEntity<?> getBuddyList();

    @GetMapping("/getPendinglist")
    public ResponseEntity<?> getPendingList();

    @PatchMapping("/updateme")
    public ResponseEntity<Boolean> updateProfile(@RequestBody PatchUserResources patchUserResources ) throws Exception;
    

    @PatchMapping("/mypicture")
    public ResponseEntity<Boolean> updateProfilePicture(@RequestParam("picture") MultipartFile picture) throws Exception;
    
}
