package com.shippingoo.service;

import com.shippingoo.resource.message.GetMessageResponse;
import com.shippingoo.resource.message.GetAMessageResponse;
import com.shippingoo.resource.message.MessagePostResource;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MessageService {
    GetAMessageResponse sendAMessage(MessagePostResource messagePostResource, Long senderId);

    Page<GetMessageResponse> getAUserMessages(long buddyId,int page);
}
