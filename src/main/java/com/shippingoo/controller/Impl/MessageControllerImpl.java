package com.shippingoo.controller.Impl;

import com.shippingoo.Service.BuddyListService;
import com.shippingoo.controller.MessageController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MessageControllerImpl implements MessageController {
    private final BuddyListService buddyListService;

    @Override
    public ResponseEntity<?> sendMessage(Long receiverId) {

        return ResponseEntity.ok(buddyListService.getASenderId(receiverId));
    }

    @Override
    public ResponseEntity<?> sendM() {
        // TODO Auto-generated method stub
        return null;
    }

}
