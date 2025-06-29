package nogrend.jobs.outbox;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OutboxRepository {

    private final JdbcClient jdbcClient;

    public OutboxRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public void save(OutboxMessage message) {
        jdbcClient.sql("""
                INSERT INTO outbox (aggregate_type, aggregate_id, event_type, payload)
                VALUES (:aggregateType, :aggregateId, :eventType, :payload)
                """)
                .param("aggregateType", message.aggregateType())
                .param("aggregateId", message.aggregateId())
                .param("eventType", message.eventType())
                .param("payload", message.payload())
                .update();
    }

    public List<OutboxMessage> findUnprocessedMessages(int limit) {
        return jdbcClient.sql("""
                SELECT * FROM outbox WHERE status = 'PENDING' ORDER BY created_at ASC LIMIT :limit
                """)
                .param("limit", limit)
                .query(OutboxMessage.class)
                .list();
    }

    public void markAsProcessed(Long id) {
        jdbcClient.sql("""
                UPDATE outbox SET status = 'PROCESSED', processed_at = CURRENT_TIMESTAMP WHERE id = :id
                """)
                .param("id", id)
                .update();
    }
}

