package com.shippingoo.Mapper;

import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDateTime;

import com.shippingoo.Entity.TempoUser;
import com.shippingoo.Resource.Auth.GetApprovedResources;
import com.shippingoo.Resource.Auth.PostRegistrationResources;
import com.shippingoo.enums.auth.TypeUser;
import com.shippingoo.exceptions.OperationFailed;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TempUserMapper {

    default TempoUser tempUserFromPostRegistration(PostRegistrationResources postRegistrationResources, String token) {
        if (postRegistrationResources == null || token == null)
            return null;
        TempoUser tempoUser = new TempoUser();
        tempoUser.setCreatedat(LocalDateTime.now());
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