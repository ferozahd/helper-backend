package com.shippingoo.service.impl;

import com.shippingoo.entity.Message;
import com.shippingoo.mapper.MessageMapper;
import com.shippingoo.repository.MessageRepository;
import com.shippingoo.resource.message.GetMessageResponse;
import com.shippingoo.resource.message.GetAMessageResponse;
import com.shippingoo.resource.message.MessagePostResource;
import com.shippingoo.service.CommonService;
import com.shippingoo.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;
    private final CommonService commonService;

    @Override
    public GetAMessageResponse sendAMessage(MessagePostResource messagePostResource, Long senderId) {
        Message message = new Message();
        message.setCommunicationId(senderId);
        message.setMessage(messagePostResource.getMessageBody());

        return messageMapper.messageToAMessageResponse(messageRepository.save(message));
    }

    @Override
    public Page<GetMessageResponse> getAUserMessages(long buddyId,int page) {
        Pageable pageable = PageRequest.of(page, 20);
        return messageRepository.getAUserMessages(buddyId, commonService.getUserIdFromContext() ,pageable);
//    return null;
    }

}
