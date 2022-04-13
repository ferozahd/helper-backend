package com.shippingoo.service.impl;

import com.shippingoo.entity.User;
import com.shippingoo.enums.auth.TypeUser;
import com.shippingoo.exceptions.ResourceNotFoundException;
import com.shippingoo.mapper.UserMapper;
import com.shippingoo.repository.UserRepository;
import com.shippingoo.resource.seller.SellerProfileResources;
import com.shippingoo.service.BuyerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BuyerServiceImpl implements BuyerService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<SellerProfileResources> getSellerProfile(Long profileId) {
        Optional<User> user = userRepository.findById(profileId);
        if (user.isPresent()) {
            if (user.get().getRole() == TypeUser.SELLER) {
                SellerProfileResources response = userMapper.userToSellerProfile(user.get());
                return ResponseEntity.ok(response);
            } else {
                throw new ResourceNotFoundException("No seller found under this Id code 1");
            }
        } else {
            throw new ResourceNotFoundException("No seller found under this Id code 2");
        }
    }
}
