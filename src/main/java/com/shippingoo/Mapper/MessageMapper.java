package com.shippingoo.mapper;

import com.shippingoo.entity.Message;
import com.shippingoo.resource.message.GetAMessageResponse;
import com.shippingoo.utility.BasicConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(uses = {BasicConverter.class},componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
@Named("MessageMapper")
public interface MessageMapper {

    @Named("messageToAMessageResponse")
    @Mapping(source = "id",target ="messageId")
    @Mapping(source = "createdAt",target ="deliverytime" , qualifiedByName = {"BasicConverter","yyMMddThhMMss"})
    @Mapping(source = "message",target ="messageBody")
    GetAMessageResponse messageToAMessageResponse(Message save);
}
