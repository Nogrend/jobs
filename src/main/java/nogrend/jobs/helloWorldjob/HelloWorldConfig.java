package nogrend.jobs.helloWorldjob;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;

import static org.quartz.CronScheduleBuilder.cronSchedule;

@Configuration
public class HelloWorldConfig {

    @Bean
    public JobDetail helloWorldJobDetail(){
        return JobBuilder.newJob().ofType(HelloWorldJob.class)
                .storeDurably()
                .withIdentity("helloWorld", "sayingSomething")
                .withDescription("Saying hello world")
                .build();
    }

    @Bean
    public Trigger helloWorldTrigger(JobDetail helloWorldJobDetail) throws ParseException {
        return TriggerBuilder.newTrigger().forJob(helloWorldJobDetail)
                .withIdentity("helloWorld", "sayingSomething")
                .withDescription("Saying hello world by cron expression")
                .withSchedule(cronSchedule(new CronExpression("0/3 * * * * ?")))
                .build();
    }
}
