package com.shippingoo.service;

import java.util.List;

import com.shippingoo.entity.BuddyList;

public interface BuddyListService {
    /* 
    this just give the list 
    when This user has buddylist with isRead false 
    isRead will false when sender send message but receiver did not read it . 
    receiver is current user which i get from token and UserContextHolder 
    */
    public Long getHanggedMessageNumber();

    /* 
    It will returns all buddy list which is active 
    */
    public List<BuddyList> getBuddyList();

    /* 
    
    */
    public Long getASenderId(Long receiver);
}
