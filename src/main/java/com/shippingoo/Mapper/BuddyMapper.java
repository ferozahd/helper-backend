package com.shippingoo.mapper;

import org.mapstruct.NullValuePropertyMappingStrategy;

import com.shippingoo.entity.BuddyList;
import com.shippingoo.entity.User;

import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BuddyMapper {

    default BuddyList buddyListFromUser(Long userid, User user,Long link){
        if(userid == null || user== null){
            return null;
        }
        BuddyList buddylist=new BuddyList();
        buddylist.setSenderId(userid);
        buddylist.setReceiverId(user.getId());
        buddylist.setStatus((byte)1);
        buddylist.setLink(link);
        buddylist.setIsRead(false);
        return buddylist;

    }

}