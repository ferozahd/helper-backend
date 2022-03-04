package com.shippingoo.Entity;

import java.time.LocalDate;

import javax.persistence.*;

import com.shippingoo.enums.notification.NotificationTypeEnums;

import org.springframework.data.annotation.CreatedDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    private boolean isRead;

    @Column( nullable = false)
    private Long userid;

    @Enumerated(EnumType.STRING)
    private NotificationTypeEnums type;

    private String notification;



    @Column(updatable = false, name = "created_at")
    @CreatedDate
    private LocalDate createdAt;

    
    @PrePersist
    private void onCreate() {
        this.isRead = false;
        this.createdAt = LocalDate.now();

    }

}
