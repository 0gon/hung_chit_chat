package chat.jwtservice.batch.repository;

import chat.jwtservice.jwt.entity.QRefreshToken;
import chat.jwtservice.jwt.entity.RefreshToken;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static chat.jwtservice.jwt.entity.QRefreshToken.refreshToken1;


@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JwtQueryRepositoryImpl {

    private final JPAQueryFactory queryFactory;

    @Transactional
    public long bulkDelete(){
        return queryFactory
                .delete(refreshToken1)
                .where(refreshToken1.expiresAt.before(LocalDateTime.now()))
                .execute();
    }
}
