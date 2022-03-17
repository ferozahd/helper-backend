package com.shippingoo.Mapper;

import org.mapstruct.NullValuePropertyMappingStrategy;


import com.shippingoo.Entity.TempoUser;
import com.shippingoo.Entity.User;
import com.shippingoo.Resource.Auth.PostCreateUser;
import com.shippingoo.Resource.Auth.PostRegistrationResources;
import com.shippingoo.Resource.user.GetMeResources;
import com.shippingoo.Resource.user.PatchUserResources;

import org.mapstruct.Mapper;
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    default User userFromPostRegister(PostRegistrationResources postRegistrationResources) {
        if (postRegistrationResources == null)
            return null;
        User user = new User();
        user.setEmail(postRegistrationResources.getEmail());
        String[] role = new String[] { postRegistrationResources.getUsertype()};
       // user.setRole(role);
       
        return user;
    }

    default User userFromTempoAndCreateResource(TempoUser tempoUser, PostCreateUser postCreateUser){
        if(tempoUser== null || postCreateUser== null)
        return null;
        User user =new User();
        user.setName(postCreateUser.getName());
        user.setPassword(postCreateUser.getPassword());
        user.setAcexpired(true);
        user.setAclocked(true);
        user.setEnabled(true);
        user.setCdexpired(true);
        user.setEmail(tempoUser.getEmail());
        user.setUsername(tempoUser.getUsername());
        user.setPhoto("http://localhost:8080/file/get/default.jpg");
        user.setRole(tempoUser.getRole());
        return user;

    }

    default GetMeResources getMeResources(User user){
        if(user==null){
            return null;
        }
        GetMeResources getMeResources =new GetMeResources();
        getMeResources.setUsername(user.getUsername());
        getMeResources.setFullname(user.getName());
        getMeResources.setProfilepicture(user.getPhoto());
        getMeResources.setRole(user.getRole().toString());
        getMeResources.setAddress(user.getAddress());
        getMeResources.setCity(user.getCity());
        getMeResources.setCountry(user.getCountry());
        getMeResources.setPostalcode(user.getPostcode());
        getMeResources.setEmail(user.getEmail());
        getMeResources.setAboutme(user.getAboutme());
        getMeResources.setPhonenumber(user.getPhone());
        return getMeResources;
    }

    default User userFromPatchResource(User user, PatchUserResources patchUserResources){
        if(user==null || patchUserResources ==null){
            return null;
        }
        user.setAboutme(patchUserResources.getAboutme());
        user.setCity(patchUserResources.getCity());
        user.setCountry(patchUserResources.getCountry());
        user.setPostcode(patchUserResources.getPostalcode());
        user.setName(patchUserResources.getFullname());
        user.setAddress(patchUserResources.getAddress());
        user.setPhone(patchUserResources.getPhonenumber());
       return user;
    }

}

