package com.shippingoo.mapper;

import org.mapstruct.NullValuePropertyMappingStrategy;


import com.shippingoo.entity.TempoUser;
import com.shippingoo.resource.auth.GetApprovedResources;
import com.shippingoo.resource.auth.PostRegistrationResources;
import com.shippingoo.enums.auth.TypeUser;
import com.shippingoo.exceptions.OperationFailed;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TempUserMapper {

    default TempoUser tempUserFromPostRegistration(PostRegistrationResources postRegistrationResources, String token) {
        if (postRegistrationResources == null || token == null)
            return null;
        TempoUser tempoUser = new TempoUser();
        tempoUser.setEmail(postRegistrationResources.getEmail());
        tempoUser.setUsername(postRegistrationResources.getUsername());
        switch (postRegistrationResources.getUsertype()) {
            case "SELLER":
                tempoUser.setRole(TypeUser.SELLER);
                break;
            case "BUYER":
                tempoUser.setRole(TypeUser.BUYER);
                break;
        } 
        tempoUser.setToken(token);
        return tempoUser;
    }

    default GetApprovedResources getApprovedResourcesFromTempoUser(TempoUser tempoUser){
        GetApprovedResources getApprovedResources =new GetApprovedResources();
        if(tempoUser==null)
        throw new OperationFailed("Failed to compile");

       
        getApprovedResources.setToken(tempoUser.getToken());

        return getApprovedResources;
    }

}