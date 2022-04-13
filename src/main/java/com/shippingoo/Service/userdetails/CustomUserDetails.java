package com.shippingoo.service.userdetails;


import java.util.Collection;
import java.util.List;

import com.shippingoo.entity.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.ToString;

public class CustomUserDetails implements UserDetails {
    private User user;
    public CustomUserDetails(User user)
    {
        this.user=user;
    }
    public String getEmail(){
        return user.getEmail();
    }
    public String getfullName()
    {
        return user.getName();
    }
    public Long getId(){
        return user.getId();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getRole().toString()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
       return user.isAcexpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isAclocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isCdexpired();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
    
}
