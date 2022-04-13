package com.shippingoo.controller.Impl;


import com.shippingoo.resource.user.GetMeResources;
import com.shippingoo.resource.user.PatchUserResources;
import com.shippingoo.service.BuddyListService;
import com.shippingoo.service.UserService;
import com.shippingoo.controller.UserController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
        return null;
    }

    @Override
    public ResponseEntity<Boolean> updateProfile(PatchUserResources patchUserResources) {
        return ResponseEntity.ok(userService.updateProfile(patchUserResources));
    }


    @Override
    public ResponseEntity<Boolean> updateProfilePicture(MultipartFile file) throws Exception {
        return ResponseEntity.ok(userService.updateProfilePicture(file));
    }

}
