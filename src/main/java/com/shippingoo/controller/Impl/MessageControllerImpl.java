package com.shippingoo.controller.Impl;

import com.shippingoo.exceptions.OperationFailed;
import com.shippingoo.resource.BuddyListGetResources;
import com.shippingoo.resource.message.GetMessageResponse;
import com.shippingoo.resource.message.GetAMessageResponse;
import com.shippingoo.resource.message.MessagePostResource;
import com.shippingoo.service.BuddyListService;
import com.shippingoo.controller.MessageController;

import com.shippingoo.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageControllerImpl implements MessageController {
    private final BuddyListService buddyListService;
    private final MessageService messageService;


    @Override
    public ResponseEntity<GetAMessageResponse> sendMessage(Long receiverId, MessagePostResource messagePostResource) {
        try {
            Long senderId = buddyListService.getASenderId(receiverId);
            return ResponseEntity.status(HttpStatus.CREATED).body(messageService.sendAMessage(messagePostResource, senderId));
        } catch (Exception e) {
            throw new OperationFailed(e.getLocalizedMessage());
        }
    }


    @Override
    public ResponseEntity<BuddyListGetResources> getBuddyList() {
        return null;
    }

    @Override
    public ResponseEntity<Page<GetMessageResponse>> getAUserMessages(String userId,int page) {
        try {
            return ResponseEntity.ok(messageService.getAUserMessages(Long.parseLong(userId),page));
        } catch (Exception e) {
            throw new OperationFailed("User id invalid " + e.getLocalizedMessage());
        }
    }

}
