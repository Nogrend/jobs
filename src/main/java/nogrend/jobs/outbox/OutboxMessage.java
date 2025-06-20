package nogrend.jobs.outbox;

import java.time.Instant;

public record OutboxMessage(
        Long id,
        String aggregateType,
        String aggregateId,
        String eventType,
        String payload,
        Instant createdAt
) {
}
