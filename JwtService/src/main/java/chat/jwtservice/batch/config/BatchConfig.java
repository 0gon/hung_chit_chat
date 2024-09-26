package chat.jwtservice.batch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
@EnableScheduling
public class BatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    /**
     *  delete query 문을 작성한 Tasklet 을 받아서 사용
     *  deleteExpiredTokensStep 라는 이름을 Step 으로 등록
     * */
    @Bean
    public Step deleteExpiredTokensStep(Tasklet deleteExpiredTokensTasklet) {
        return new StepBuilder("deleteExpiredTokensStep", jobRepository)
                .tasklet(deleteExpiredTokensTasklet, transactionManager)
                .build();
    }

    /**
     * 등록된 Step 을 Job 으로 만듦
     * */
    @Bean
    public Job deleteExpiredTokensJob(Step deleteExpiredTokensStep) {
        return new JobBuilder("deleteExpiredTokensJob", jobRepository)
                .start(deleteExpiredTokensStep)
                .build();
    }

}
