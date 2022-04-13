package com.shippingoo.controller;

import com.shippingoo.resource.user.GetMeResources;
import com.shippingoo.resource.user.PatchUserResources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
