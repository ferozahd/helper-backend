package com.shippingoo.repository;

import java.time.LocalDateTime;

import com.shippingoo.entity.BuddyList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuddylistRepository extends JpaRepository<BuddyList, Long> {

    List<BuddyList> findByReceiverId(Long userId);

    Long countByReceiverIdAndIsRead(Long userId, boolean b);

    Optional<BuddyList> findBySenderIdAndReceiverId(Long receiver, Long userid);

    void deleteBySenderIdAndReceiverId(Long userid, Long receiver);


    
    // @Query("select u from BuddyList u WHERE u.sender=:senderId order by u.updatedAt desc")
    // public List<BuddyList> getAllBuddyBySender(@Param("senderId") Long senderId);

    // @Query("select u from BuddyList u WHERE u.sender=:senderId and u.receiver=:receiverId")
    // public BuddyList isExistInList(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);

    // @Query("select u.sender from BuddyList u WHERE u.receiver=:senderId and u.reciverLink=null")
    // public List<Long> getAllPendingBuddyBySender(@Param("senderId") Long senderId);

    // @Modifying
    // @Query("update BuddyList buddy SET buddy.updatedAt =:currentTime WHERE buddy.id=:id")
    // public int updateCreatedAt(@Param("id") Long id, @Param("currentTime") LocalDateTime currentTime);

    // @Modifying
    // @Query("update BuddyList b set b.updatedAt=:currentTime WHERE b.id=:id or b.id=:receiver")
    // public int updateTwoCreated(@Param("id") Long id, @Param("receiver") Long reciverLink,@Param("currentTime") LocalDateTime currentTime);

    // @Query("select u from BuddyList u WHERE u.sender=:senderId")
    // public BuddyList getCommunicationIdAndLink(Long buddyId);

}
