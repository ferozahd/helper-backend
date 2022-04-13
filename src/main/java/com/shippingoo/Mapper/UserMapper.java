package com.shippingoo.mapper;

import com.shippingoo.resource.seller.SellerProfileResources;
import com.shippingoo.utility.BasicConverter;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;


import com.shippingoo.entity.TempoUser;
import com.shippingoo.entity.User;
import com.shippingoo.resource.auth.PostCreateUser;
import com.shippingoo.resource.auth.PostRegistrationResources;
import com.shippingoo.resource.user.GetMeResources;
import com.shippingoo.resource.user.PatchUserResources;

import org.mapstruct.Mapper;


@Mapper(uses = {BasicConverter.class},componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
@Named("UserMapper")
public interface UserMapper {

    default User userFromPostRegister(PostRegistrationResources postRegistrationResources) {
        if (postRegistrationResources == null)
            return null;
        User user = new User();
        user.setEmail(postRegistrationResources.getEmail());

        // user.setRole(role);
        return user;
    }

    default User userFromTempoAndCreateResource(TempoUser tempoUser, PostCreateUser postCreateUser) {
        if (tempoUser == null || postCreateUser == null)
            return null;
        User user = new User();
        user.setName(postCreateUser.getName());
        user.setPassword(postCreateUser.getPassword());
        user.setAcexpired(true);
        user.setAclocked(true);
        user.setEnabled(true);
        user.setCdexpired(true);
        user.setEmail(tempoUser.getEmail());
        user.setUsername(tempoUser.getUsername());
        user.setPhoto(null);
        user.setRole(tempoUser.getRole());
        return user;

    }

    @Named("getMeResources")
    GetMeResources getMeResources(User user);

    //    default GetMeResources getMeResources(User user){
//        GetMeResources getMeResources =new GetMeResources();
//        getMeResources.setUsername(user.getUsername());
//        getMeResources.setFullname(user.getName());
//        if(user.getPhoto()==null){
//            getMeResources.setProfilepicture("http://localhost:5000/file/get/default.jpg");
//        }else{
//              getMeResources.setProfilepicture(user.getPhoto());
//        }
//
//        getMeResources.setRole(user.getRole().toString());
//        getMeResources.setAddress(user.getAddress());
//        getMeResources.setCity(user.getCity());
//        getMeResources.setCountry(user.getCountry());
//        getMeResources.setPostalcode(user.getPostcode());
//        getMeResources.setEmail(user.getEmail());
//        getMeResources.setAboutme(user.getAboutme());
//        getMeResources.setPhonenumber(user.getPhone());
//        return getMeResources;
//    }
//
//

    default User userFromPatchResource(User user, PatchUserResources patchUserResources) {
        if (user == null || patchUserResources == null) {
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

    @Named("userToSellerProfile")
    @Mapping(source = "createdAt", target = "createdAt",qualifiedByName ={"BasicConverter","createdAtToyyyuMMdd"} )
    SellerProfileResources userToSellerProfile(User user);
}

