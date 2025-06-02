package nogrend.jobs;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ConsolePinger {

    private final ApplicationContext context;
    private final CounterRepository counterRepository;

    public ConsolePinger(ApplicationContext context, CounterRepository counterRepository) {
        this.context = context;
        this.counterRepository = counterRepository;
    }

    @Scheduled(fixedRate = 2000)
    @SchedulerLock(name = "ConsolePinger_ping",
            lockAtMostFor = "1s",  // Max lock duration if the node crashes
            lockAtLeastFor = "500ms")  // Minimum lock time to prevent overlapping runs
    public void ping() {
        String appName = context.getEnvironment().getProperty("spring.application.name");
        System.out.println("Ping! from " + appName);

        var counter = counterRepository.getCounter("main_counter").stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No main counter found"));

        System.out.println("Counter: " + counter);

        counterRepository.incrementCounter("main_counter");

        try {
            // If you need a delay
            Thread.sleep(5000); // 1 second delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(appName + " has finished");
    }
}
