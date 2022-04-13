package com.shippingoo.controller;

import com.shippingoo.resource.BuddyListGetResources;
import com.shippingoo.resource.message.GetMessageResponse;
import com.shippingoo.resource.message.GetAMessageResponse;
import com.shippingoo.resource.message.MessagePostResource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/message")
public interface MessageController {
    // @PostMapping("/")
    // public ResponseEntity<?> sendMessage();

    @PostMapping("/send/{id}")
    public ResponseEntity<GetAMessageResponse> sendMessage(@PathVariable("id") Long receiverId, @RequestBody MessagePostResource messagePostResource);

    @GetMapping("/buddylist")
    ResponseEntity<BuddyListGetResources> getBuddyList();

    @GetMapping("/get/{userId}/{page}")
    public ResponseEntity<Page<GetMessageResponse>> getAUserMessages(@PathVariable String userId,@PathVariable int page);

    // @GetMapping("/get/{usename}")6869

}
