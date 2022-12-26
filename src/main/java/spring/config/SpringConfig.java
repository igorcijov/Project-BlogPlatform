package spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Configuration
//@EnableScheduling
public class SpringConfig {

    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask() throws InterruptedException {
        System.out.println(
                "Fixed delay task - " +  new Date());
      Thread.sleep(1000);
    }

    @Scheduled(cron = "0 52 19 * * ?")
    public void scheduleFixedDelayTaskCron() throws InterruptedException {
        System.out.println(
                "Fixed delay task - " + new Date());
    }
}
