package com.shippingoo.Service.impl;

import java.security.Principal;
import java.util.Optional;

import com.shippingoo.Entity.User;
import com.shippingoo.Mapper.UserMapper;
import com.shippingoo.Resource.user.GetMeResources;
import com.shippingoo.Service.BuddyListService;
import com.shippingoo.Service.CommonService;
import com.shippingoo.Service.UserService;
import com.shippingoo.exceptions.ResourceNotFoundException;
import com.shippingoo.repository.UserRepository;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    private final CommonService commonService;
    private final BuddyListService buddyListService;


    @Override
    public GetMeResources getMe() {

        GetMeResources getMeResources = new GetMeResources();
        /*
         * This section will find the username from the principal
         * then fetch the complete user details with help of UserRepository Query
         */
  
        Optional<User> findUser = userRepository.findByUsername(commonService.getUsernameFromContext());
        User user = null;
        if (findUser.isPresent()) {
            user = findUser.get();
            getMeResources.setUsername(user.getUsername());
            getMeResources.setFullname(user.getName());
            getMeResources.setProfilepicture(user.getPhoto());
            getMeResources.setRole(user.getRole().toString());
        } else {
           throw new ResourceNotFoundException("User not found");
        }

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
    }

}
