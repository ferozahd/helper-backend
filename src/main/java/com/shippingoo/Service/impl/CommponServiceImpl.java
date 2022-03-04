package com.shippingoo.Service.impl;

import com.shippingoo.Service.CommonService;
import com.shippingoo.utils.UserContextHolder;

import org.springframework.stereotype.Service;
@Service
public class CommponServiceImpl implements CommonService{

    @Override
    public String getToken() {
        return UserContextHolder.getToken();
    }



    @Override
    public String getUsernameFromContext() {
        return UserContextHolder.getUsername();
    }

    @Override
    public Long getUserIdFromContext() {
        return UserContextHolder.getUserId();
    }



}
