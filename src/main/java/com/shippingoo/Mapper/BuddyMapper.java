package com.shippingoo.Mapper;

import org.mapstruct.NullValuePropertyMappingStrategy;

import com.shippingoo.Entity.BuddyList;
import com.shippingoo.Entity.User;

import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BuddyMapper {

    default BuddyList buddyListFromUser(Long userid, User user,Long link){
        if(userid == null || user== null){
            return null;
        }
        BuddyList buddylist=new BuddyList();
         
        buddylist.setSenderId(userid);
                
        buddylist.setReceiverPicture(user.getPhoto());
        buddylist.setReceiverId(user.getId());
        buddylist.setReceiverUsername(user.getUsername());
        buddylist.setReceiverName(user.getName());

        buddylist.setStatus((byte)1);
        buddylist.setLink(link);

        return buddylist;

    }

}