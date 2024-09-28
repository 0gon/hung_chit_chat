package chat.jwtservice.batch.tasklet;

import chat.jwtservice.batch.repository.JwtQueryRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
@StepScope
@RequiredArgsConstructor
@Log4j2
public class DeleteExpiredTokensTasklet implements Tasklet {

    private final JwtQueryRepositoryImpl jwtQueryRepository;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        long deletedCount = jwtQueryRepository.bulkDelete();
        log.info("Deleting expired tokens : {}", deletedCount);
        return RepeatStatus.FINISHED;
    }
}
