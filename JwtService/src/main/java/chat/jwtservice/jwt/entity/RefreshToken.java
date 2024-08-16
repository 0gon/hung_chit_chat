package chat.jwtservice.jwt.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class RefreshToken {

    @Id @GeneratedValue
    private Long id;

    private Long userId;

    private String refreshToken;

    @CreatedDate
    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;

    @Builder
    public RefreshToken(Long id, Long userId, String refreshToken, LocalDateTime createdAt, LocalDateTime expiresAt) {
        this.id = id;
        this.userId = userId;
        this.refreshToken = refreshToken;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }

    @PrePersist
    public void prePersist() {
        this.expiresAt = this.createdAt.plusDays(3); // 생성일 + 3일
    }
}
