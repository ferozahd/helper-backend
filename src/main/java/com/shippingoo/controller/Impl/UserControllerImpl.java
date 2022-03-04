package com.shippingoo.controller.Impl;

import java.security.Principal;

import com.shippingoo.Resource.user.GetMeResources;
import com.shippingoo.Service.BuddyListService;
import com.shippingoo.Service.CommonService;
import com.shippingoo.Service.UserService;
import com.shippingoo.controller.UserController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService userService;
   private final BuddyListService buddyListService;

    @Override
    public ResponseEntity<GetMeResources> getMe() {
        return ResponseEntity.ok(userService.getMe());
    }


    @Override
    public ResponseEntity<?> getBuddyList() {
        return ResponseEntity.ok(buddyListService.getBuddyList());
    }


    @Override
    public ResponseEntity<?> getPendingList() {
        // TODO Auto-generated method stub
        return null;
    }

}
