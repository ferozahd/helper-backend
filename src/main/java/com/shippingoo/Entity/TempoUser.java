package com.shippingoo.Entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.shippingoo.enums.auth.TypeUser;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class TempoUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeUser role;
    @Column(unique = true,nullable =  false)
    private String email;
    @Column(unique = true,nullable = false)
    private String username;
    @Column(nullable =  false,unique = true)
    private String token;
    private LocalDateTime createdat;
}
