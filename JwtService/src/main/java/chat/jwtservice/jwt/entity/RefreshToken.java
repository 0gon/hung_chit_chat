package chat.jwtservice.jwt.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    private String memberId;

    private String email;

    private String refreshToken;

    private String role;

    @CreatedDate
    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;

    @Builder
    public RefreshToken(Long id, String memberId, String email, String refreshToken, String role, LocalDateTime createdAt, LocalDateTime expiresAt) {
        this.id = id;
        this.memberId = memberId;
        this.email = email;
        this.refreshToken = refreshToken;
        this.role = role;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }

    // Persist 전 실행 -> 데이터 생성된 일로부터 +3 일
    @PrePersist
    public void prePersist() {
        this.expiresAt = this.createdAt.plusDays(3); // 생성일 + 3일
    }
}
