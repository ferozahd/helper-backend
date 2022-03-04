package com.shippingoo.Service.impl;

import com.shippingoo.Service.NotificationService;
import com.shippingoo.repository.NotificationRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    @Override
    public int getHanggedNumber() {
        // TODO Auto-generated method stub
        return 0;
    }

}
