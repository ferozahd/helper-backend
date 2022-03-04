package com.shippingoo.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    // Communication Id
    @Column(name = "cid", nullable = false)
    private Long communicationId;

    private String message;



    @Column(updatable = false)
    private LocalDate createdAt;

    @Column(updatable = false)
    private LocalTime deliveryTime;


    @PrePersist
    private void onCreate() {
        createdAt = LocalDate.now();
        deliveryTime = LocalTime.now();
    }

}
