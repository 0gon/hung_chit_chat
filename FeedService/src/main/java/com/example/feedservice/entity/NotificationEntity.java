package com.example.feedservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "notifications")
public class NotificationEntity extends BaseEntity {

    @Id
    @Column(name = "notification_id")
    private String notificationId;

    private String relatedId;

    private String relatedType;

    private String subMemberId;

    private String memberId;

    private Boolean isRead;
}
