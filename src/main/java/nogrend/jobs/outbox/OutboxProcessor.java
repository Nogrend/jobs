package nogrend.jobs.outbox;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OutboxProcessor {

    private final OutboxRepository outboxRepository;


    public OutboxProcessor(OutboxRepository outboxRepository) {
        this.outboxRepository = outboxRepository;
    }

    @Scheduled(fixedRate = 10_000)
    @SchedulerLock(name = "ConsoleWriter_outbox",
            lockAtMostFor = "2s")  // Max lock duration if the node crashes
//            lockAtLeastFor = "6s")  // Minimum lock time to prevent overlapping runs
    public void processOutbox() {
        var messages = this.outboxRepository.findUnprocessedMessages(2);
        for (OutboxMessage message : messages) {
            try {
                System.out.println(message);
                outboxRepository.markAsProcessed(message.id());
            } catch (Exception e) {
                // optionally log or increment retry count
            }
        }
    }
}
