package com.shippingoo.service;

import com.shippingoo.resource.user.GetMeResources;
import com.shippingoo.resource.user.PatchUserResources;

import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    GetMeResources getMe();

    Boolean updateProfile(PatchUserResources patchUserResources);

    Boolean updateProfilePicture(MultipartFile file);
    
}
