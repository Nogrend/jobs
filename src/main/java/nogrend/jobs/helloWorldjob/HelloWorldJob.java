package nogrend.jobs.helloWorldjob;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldJob implements Job {

    private final Logger logger = LoggerFactory.getLogger(HelloWorldJob.class.getName());

    private static int counter = 0;

    @Override
    public void execute(JobExecutionContext context) {
        System.out.println(counter++ + " hello world");

        var jobName = context.getJobDetail().getKey().getName();
        logger.info("Name of Job: {}", jobName);
        logger.debug("Name of Job: {}", jobName);
        logger.warn("Name of Job: {}", jobName);
        logger.error("Name of Job: {}", jobName);
    }
}
