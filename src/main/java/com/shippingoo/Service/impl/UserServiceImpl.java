package com.shippingoo.Service.impl;

import java.util.Date;
import java.util.Optional;

import com.shippingoo.Entity.User;
import com.shippingoo.Mapper.UserMapper;
import com.shippingoo.Resource.user.GetMeResources;
import com.shippingoo.Resource.user.PatchUserResources;
import com.shippingoo.Service.BuddyListService;
import com.shippingoo.Service.CommonService;
import com.shippingoo.Service.UserService;
import com.shippingoo.Service.storage.StorageService;
import com.shippingoo.exceptions.OperationFailed;
import com.shippingoo.exceptions.ResourceNotFoundException;
import com.shippingoo.repository.UserRepository;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    private final CommonService commonService;
    private final BuddyListService buddyListService;
    private final StorageService storageService;

    @Override
    public GetMeResources getMe() {

        Optional<User> findUser = userRepository.findByUsername(commonService.getUsernameFromContext());

        if (findUser.isPresent()) {

            GetMeResources getMeResources = userMapper.getMeResources(findUser.get());
            /*
             * After checking every thing if user is valid with jwt token then user will be
             * found here
             * other wise it will return Un-Authorized user
             */

            /*
             * find hangedMessage here
             */
            getMeResources.setHangedMessage(buddyListService.getHanggedMessageNumber());
            /*
             * find hangedNotification here
             */
            getMeResources.setHangedNotification(0l);

            return getMeResources;

        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @Override
    public Boolean updateProfile(PatchUserResources patchUserResources) {
        try {
            User user = userRepository.findByUsername(commonService.getUsernameFromContext()).get();
            user = userMapper.userFromPatchResource(user, patchUserResources);
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            throw new OperationFailed(e.getLocalizedMessage());
        }

    }

    @Override
    public Boolean updateProfilePicture(MultipartFile file) {

            User user = userRepository.findByUsername(commonService.getUsernameFromContext()).get();

            String filename = user.getId().toString() + "." + new Date().getTime() + "." + Math.random() + ".jpg";
            Boolean profilepicstatus = storageService.save(file, filename);
            storageService.deleteOne(user.getPhoto().replace("http://localhost:8080/file/get/", ""));
            user.setPhoto("http://localhost:8080/file/get/"+filename);
            userRepository.save(user);
        return profilepicstatus;
    }

}
