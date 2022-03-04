package com.shippingoo.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

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
    private String receiverUsername;

    @Column(updatable = false)
    private Long receiverId;


    @Column(updatable = true, nullable = false)
    private String receiverName;


    @Column(updatable = true, nullable = false)
    private String ReceiverPicture;

    // for seen is 0 for unseen -1 and number of unseen is exact value of this raw .
    @Column(columnDefinition = "Boolean default false")
    private Boolean isRead;
    
    private Long link;

    @Column(updatable = false, name = "created_at")
    @CreatedDate
    private LocalDate createdAt;

    @Column(updatable = true, name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // status 1 is active
    // 0 is block 
    // -1 is deactive
    // -2 that means pending
    private byte status;

    @PrePersist
    private void onCreate() {
        this.status = 1;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDateTime.now();
    }

}
