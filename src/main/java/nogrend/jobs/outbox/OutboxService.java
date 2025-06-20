package nogrend.jobs.outbox;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class OutboxService {
    private final OutboxRepository outboxRepository;

    public OutboxService(OutboxRepository outboxRepository) {
        this.outboxRepository = outboxRepository;
    }

    public void save(Long id, String message) {
        var outboxMessage = new OutboxMessage(
                id,
                "SomeAggregate",
                id.toString(),
                "SomeEventType",
                message,
                Instant.now()
        );
        this.outboxRepository.save(outboxMessage);
    }

    public List<OutboxMessage> getByLimit(int limit) {
        return this.outboxRepository.findUnprocessedMessages(limit);
    }

}
