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
                "SomeAggregate",        // aggregateType
                id.toString(),          // aggregateId (you can change this as needed)
                "SomeEventType",        // eventType
                message,                // payload
                Instant.now()           // createdAt
        );
        this.outboxRepository.save(outboxMessage);
    }

    public List<OutboxMessage> getByLimit(int limit) {
        return this.outboxRepository.findUnprocessedMessages(limit);
    }

}
