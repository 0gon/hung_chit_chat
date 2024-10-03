package com.example.feedservice.notification.entity;

import com.example.feedservice.common.entity.BaseEntity;
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

    @Override
    public String getId() {
        return this.notificationId;
    }
}
