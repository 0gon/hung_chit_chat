package chat.jwtservice.batch.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static chat.jwtservice.jwt.entity.QRefreshToken.refreshToken1;


@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Log4j2
public class JwtQueryRepositoryImpl {

    private final JPAQueryFactory queryFactory;

    @Transactional
    public long bulkDelete(){
        log.info("DELETE BULK DELETE");

        return queryFactory
                .delete(refreshToken1)
                .where(refreshToken1.expiresAt.before(LocalDateTime.now()))
                .execute();
    }
}
