package nogrend.jobs.helloWorldjob;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class HelloWorldJob implements Job {

    private static int counter = 0;

    @Override
    public void execute(JobExecutionContext context) {
        System.out.println(counter++ + " hello world");
        System.out.println("Name of Job:     " + context.getJobDetail().getKey().getName());
        System.out.println("Name of Trigger: " + context.getTrigger().getKey().getName());
    }
}
