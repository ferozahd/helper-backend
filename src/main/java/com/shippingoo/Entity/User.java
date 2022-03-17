package com.shippingoo.Entity;

import lombok.Data;

import javax.persistence.*;

import com.shippingoo.enums.auth.TypeUser;

@Entity
@Data
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "username", nullable = false, updatable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    private TypeUser role;


    @Column(name = "email", nullable = false, updatable = false,unique = true)
    private String email;
    private String phone;

    private String city;
    private String country;
    private String postcode;
    private String address;
    private String aboutme;
    
    private String photo;

    private boolean acexpired;
    private boolean aclocked;
    private boolean cdexpired;
    private boolean enabled;

}
