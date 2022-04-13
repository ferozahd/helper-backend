package com.shippingoo.service.impl;

import com.shippingoo.service.CommonService;
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

    @Override
    public String getFullNameFromContext() {
        return UserContextHolder.getFullName();
    }


}
