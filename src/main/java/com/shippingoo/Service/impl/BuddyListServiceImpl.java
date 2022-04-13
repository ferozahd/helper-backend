package com.shippingoo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.shippingoo.entity.BuddyList;
import com.shippingoo.entity.User;
import com.shippingoo.mapper.BuddyMapper;
import com.shippingoo.service.BuddyListService;
import com.shippingoo.service.CommonService;
import com.shippingoo.exceptions.OperationFailed;
import com.shippingoo.exceptions.ResourceNotFoundException;
import com.shippingoo.repository.BuddylistRepository;
import com.shippingoo.repository.UserRepository;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuddyListServiceImpl implements BuddyListService {
    private Logger log = Logger.getLogger(BuddyListServiceImpl.class.getName());
    private final BuddylistRepository buddylistRepository;
    private final CommonService commonService;
    private final UserRepository userRepository;
    private final BuddyMapper buddyMapper = Mappers.getMapper(BuddyMapper.class);

    @Override
    public List<BuddyList> getBuddyList() {
        Long userId = commonService.getUserIdFromContext();
        List<BuddyList> buddyList = buddylistRepository.findByReceiverId(userId);
        return buddyList;
    }

    @Override
    public Long getHanggedMessageNumber() {
        try {
            Long userId = commonService.getUserIdFromContext();
            return buddylistRepository.countByReceiverIdAndIsRead(userId, false);
        } catch (Exception e) {
            throw new OperationFailed(e.getLocalizedMessage());
        }

    }

    @Override
    public Long getASenderId(Long receiver) {
        Long userid = commonService.getUserIdFromContext();
        if (receiver == userid) {
            throw new OperationFailed("You can't message your self");
        }
        Optional<BuddyList> getBuddy = buddylistRepository.findBySenderIdAndReceiverId(receiver, userid);
        if (getBuddy.isPresent()) {
            BuddyList buddylist = getBuddy.get();

           if(buddylist.getStatus()!=1){
             throw new OperationFailed("You're blocked");
             }

            if (buddylist.getLink() != null) {
                return buddylist.getLink();
            } else {
                try {
                    buddylistRepository.deleteBySenderIdAndReceiverId(userid, receiver);
                    Optional<User> receiveUser = userRepository.findById(receiver);
                    if (receiveUser.isPresent()) {

                        BuddyList receiverBuddy = buddyMapper.buddyListFromUser(userid, receiveUser.get(),
                                buddylist.getId());
                        receiverBuddy = buddylistRepository.save(receiverBuddy);
                        buddylist.setLink(receiverBuddy.getId());
                        buddylistRepository.save(buddylist);
                        return receiverBuddy.getId();
                    } else {
                        throw new ResourceNotFoundException("Receiver's Id is not currect");
                    }
                } catch (Exception e) {
                    throw new OperationFailed(e.getLocalizedMessage());
                }

            }
        } else {
            /*
             * Sender giving first message to receiver
             */

            Optional<BuddyList> getMe = buddylistRepository.findBySenderIdAndReceiverId(userid, receiver);
            if (getMe.isPresent()) {
                return getMe.get().getId();
            } else {
                Optional<User> receiveUser = userRepository.findById(receiver);
                if (receiveUser.isPresent()) {

                    BuddyList receiverBuddy = buddyMapper.buddyListFromUser(userid, receiveUser.get(), null);

                    receiverBuddy = buddylistRepository.save(receiverBuddy);
                    return receiverBuddy.getId();

                } else {
                    throw new ResourceNotFoundException("Receiver's Id is not currect");
                }
            }
        }

    }

}
