package com.shippingoo.service;


public interface CommonService {

    public String getToken();

    public String getUsernameFromContext();
    public Long getUserIdFromContext();
    public String getFullNameFromContext();
}
