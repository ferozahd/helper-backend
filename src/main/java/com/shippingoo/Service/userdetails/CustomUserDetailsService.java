package com.shippingoo.service.userdetails;

import java.util.Optional;
import java.util.logging.Logger;

import com.shippingoo.entity.User;
import com.shippingoo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    Logger log = Logger.getLogger(CustomUserDetails.class.getName());
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return new CustomUserDetails(user.get());
        }
        throw new UsernameNotFoundException("User name not found");

    }

}
