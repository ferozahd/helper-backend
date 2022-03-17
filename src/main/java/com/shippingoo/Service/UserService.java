package com.shippingoo.Service;

import com.shippingoo.Resource.user.GetMeResources;
import com.shippingoo.Resource.user.PatchUserResources;

import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    GetMeResources getMe();

    Boolean updateProfile(PatchUserResources patchUserResources);

    Boolean updateProfilePicture(MultipartFile file);
    
}
