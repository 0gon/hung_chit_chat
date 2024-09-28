package chat.jwtservice.batch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
//@EnableBatchProcessing        // 스프링 배치 기본설정 활성화, 메타데이터 테이블을 사용해서 배치 작업의 상태와 실행 기록관리
@RequiredArgsConstructor
@EnableScheduling               // 단순 스케줄링 기능만 활성화, 메타데이터 테이블 필요X, 단순한 작업은 실행 가능
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
                .incrementer(new RunIdIncrementer())
                .start(deleteExpiredTokensStep)
                .build();
    }

}
