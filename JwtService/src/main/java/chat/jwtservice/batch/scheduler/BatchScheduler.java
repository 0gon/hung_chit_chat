package chat.jwtservice.batch.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
    @Scheduled(cron = "*/10 * * * * ?")
    public void runBatchJob() {
        try {
            log.info("크론식 테스트 10초마다 실행");
            jobLauncher.run(deleteExpiredTokensJob, new JobParameters());
        } catch (Exception e) {
            log.error(e);
        }
    }
}
