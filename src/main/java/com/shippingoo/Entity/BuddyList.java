package com.shippingoo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
@Table(name = "buddylist")
public class BuddyList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(updatable = false)
    private Long senderId;



    @Column(updatable = false)
    private Long receiverId;



    // for seen is 0 for unseen -1 and number of unseen is exact value of this raw .
    @Column(columnDefinition = "Boolean default false")
    private Boolean isRead;
    
    private Long link;


    @Setter(AccessLevel.NONE)
    private LocalDate createdAt;

    @Setter(AccessLevel.NONE)
    private LocalDateTime updatedAt;

    // status 1 is active
    // 0 is block 
    // -1 is deactive
    // -2 that means pending
    private byte status;

    @PreUpdate
    private void onUpdate(){
        this.updatedAt=LocalDateTime.now();
    }

    @PrePersist
    private void onCreate() {
        this.status = 1;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDateTime.now();
        this.updatedAt= LocalDateTime.now();
    }

}
