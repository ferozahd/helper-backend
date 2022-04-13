package com.shippingoo.repository;

import com.shippingoo.entity.Message;
import com.shippingoo.resource.message.GetMessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface MessageRepository extends JpaRepository<Message,Long> {

@Query(value = "select new com.shippingoo.resource.message.GetMessageResponse(b.senderId,m.message,m.createdAt) from BuddyList as b LEFT JOIN Message as m on m.communicationId=b.id WHERE (b.senderId=:buddyId AND b.receiverId=:me) OR  (b.senderId=:me AND b.receiverId=:buddyId)",nativeQuery = false)
Page<GetMessageResponse> getAUserMessages(@Param("buddyId")long buddyId, @Param("me") Long me, Pageable pageable);
}
