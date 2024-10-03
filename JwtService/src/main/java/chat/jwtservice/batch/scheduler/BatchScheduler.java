package chat.jwtservice.batch.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Log4j2
@Component
@RequiredArgsConstructor
public class BatchScheduler {

    private final JobLauncher jobLauncher;

    // Config 에서 등록된 Job @Bean 을 주입받아서 사용
    private final Job deleteExpiredTokensJob;

    /**
     * 매일 자정에 실행
     * 크론식
     * 첫번째 0 -> 0초
     * 두번째 0 -> 0분
     * 세번째 0 -> 0시
     * 네번째 * -> 일(모든날)
     * 다섯번쨰 * -> 월(모든월)
     * 여섯번째 ? -> 요일(특정 요일 무시) -> 매일
     * */
    @Scheduled(cron = "${spring.batch.scheduler.cron}")        // 2일마다 새벽 4시 실행
    public void runBatchJob() {

        try {
            // 현재 시간을 고유한 파라미터로 추가 -> 배치 작업 계속 작동
            JobParameters jobParameters = new JobParametersBuilder()
                    .addDate("runDate", new Date())
                            .toJobParameters();

            jobLauncher.run(deleteExpiredTokensJob, jobParameters);
        } catch (Exception e) {
            log.error(e);
        }
    }
}
