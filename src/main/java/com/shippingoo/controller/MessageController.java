package com.shippingoo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin("*")
@RequestMapping("/message")
public interface MessageController {
    // @PostMapping("/")
    // public ResponseEntity<?> sendMessage();

    @PostMapping("/send/{id}")
    public ResponseEntity<?> sendMessage(@PathVariable("id")Long receiverId);
    @PostMapping("/s")
    public ResponseEntity<?> sendM();
    // @GetMapping("/get/{usename}")6869

}
