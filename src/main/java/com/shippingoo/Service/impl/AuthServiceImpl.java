package com.shippingoo.service.impl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.transaction.Transactional;

import com.shippingoo.entity.TempoUser;
import com.shippingoo.entity.User;
import com.shippingoo.Resource.FeedbackMessage;
import com.shippingoo.resource.auth.*;

import com.shippingoo.service.AuthService;
import com.shippingoo.config.JWTTokenHelper;
import com.shippingoo.exceptions.EmailAlreadyExist;
import com.shippingoo.exceptions.InvalidValue;
import com.shippingoo.exceptions.OperationFailed;
import com.shippingoo.exceptions.UsernameAlreadyExist;
import com.shippingoo.repository.TempUserRepository;
import com.shippingoo.repository.UserRepository;
import com.shippingoo.mapper.TempUserMapper;
import com.shippingoo.mapper.UserMapper;

import org.jboss.logging.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private Logger log = Logger.getLogger(AuthServiceImpl.class.getName());
    private final UserRepository userRepository;
    private final TempUserRepository tempUserRepository;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    private final TempUserMapper tempUserMapper = Mappers.getMapper(TempUserMapper.class);
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTTokenHelper jwtTokenHelper;

    @Override
    public ResponseEntity<GetApprovedResources> approvedToken(PostApprovedToken postApprovedToken) {
        try {
            if (postApprovedToken.getToken() == null) {
                throw new InvalidValue("invalid token");
            }

            Optional<TempoUser> tempoUser = tempUserRepository.findByToken(postApprovedToken.getToken());
            if (tempoUser.isPresent() == false) {
                throw new OperationFailed("Your token not present, try again or registard again");
            } else {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(tempUserMapper.getApprovedResourcesFromTempoUser(tempoUser.get()));
            }
        } catch (Exception e) {
            throw new OperationFailed(e.getLocalizedMessage());
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> createUser(PostCreateUser postCreateUser) {
        Optional<TempoUser> tempoUser = tempUserRepository.findByToken(postCreateUser.getToken());
        if (tempoUser.isPresent()) {
            TempoUser tmpUser = tempoUser.get();
            User user = userMapper.userFromTempoAndCreateResource(tmpUser, postCreateUser);
            try {

               
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user = userRepository.save(user); 
                tempUserRepository.deleteById(tmpUser.getId());
                return ResponseEntity.ok(user);

            } catch (Exception e) {
                throw new OperationFailed(e.getLocalizedMessage());
            }

        } else {
            throw new OperationFailed("token expire try again");
        }

    }

    @Override
    public ResponseEntity<?> login(PostLogin postLogin) throws InvalidKeySpecException, NoSuchAlgorithmException {
        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(postLogin.getUsername(), postLogin.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

            String jwttoken = jwtTokenHelper.generateToken(authentication);
            LoginFeedback loginFeedback = new LoginFeedback(jwttoken);

            return ResponseEntity.ok(loginFeedback);
        } catch (BadCredentialsException e) {
           throw new BadCredentialsException(e.getLocalizedMessage());
        }
    }

    @Override
    public ResponseEntity<FeedbackMessage> registration(PostRegistrationResources postRegistrationResources) {

        if (userRepository.countByEmail(postRegistrationResources.getEmail()) != 0) {
            throw new EmailAlreadyExist("This email already taken");
        }else if (tempUserRepository.countByEmail(postRegistrationResources.getEmail()) != 0) {
            throw new EmailAlreadyExist("This email pended , check your mail first");
        }
        if(userRepository.countByUsername(postRegistrationResources.getUsername())!=0){
         throw new UsernameAlreadyExist("This username already taken");   
        }else if(tempUserRepository.countByUsername(postRegistrationResources.getUsername())!=0){
            throw new UsernameAlreadyExist("This username pended, try with outer name ");
        }

        Pattern emailPattern = Pattern.compile("^(.+)@(.+)$");
        Matcher matcher = emailPattern.matcher(postRegistrationResources.getEmail());
        if (postRegistrationResources.getEmail() == null || matcher.matches() != true) {
            throw new InvalidValue("Email invalid");
        }

        if (postRegistrationResources.getUsertype() == null ||
                (postRegistrationResources.getUsertype().compareTo("SELLER") != 0
                        && postRegistrationResources.getUsertype().compareTo("BUYER") != 0)) {
            throw new InvalidValue("User type invalid");
        }

        if (postRegistrationResources.getUsername().length() < 6
                || postRegistrationResources.getUsername().length() > 32
                || postRegistrationResources.getUsername().matches("^\\s*$") == true) {
            throw new InvalidValue("Username should be 6 to 32 with single word ");
        }

        TempoUser tempoUser = tempUserMapper.tempUserFromPostRegistration(postRegistrationResources,
                UUID.randomUUID().toString());
        try {
            tempoUser = tempUserRepository.save(tempoUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(new FeedbackMessage("User successfuly registered"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FeedbackMessage("User failed registered"));
        }

    }

}
