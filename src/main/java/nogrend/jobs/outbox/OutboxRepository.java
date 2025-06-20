package nogrend.jobs.outbox;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OutboxRepository {

    private final JdbcTemplate jdbcTemplate;

    public OutboxRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(OutboxMessage message) {
        var sql = "INSERT INTO outbox (aggregate_type, aggregate_id, event_type, payload) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, message.aggregateType(), message.aggregateId(), message.eventType(), message.payload());
    }

    public List<OutboxMessage> findUnprocessedMessages(int limit) {
        var sql = "SELECT * FROM outbox WHERE status = 'PENDING' ORDER BY created_at ASC LIMIT ?";
        return jdbcTemplate.query(sql, new OutboxMessageRowMapper(), limit);
    }

    public void markAsProcessed(Long id) {
        var sql = "UPDATE outbox SET status = 'PROCESSED', processed_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class OutboxMessageRowMapper implements RowMapper<OutboxMessage> {
        @Override
        public OutboxMessage mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new OutboxMessage(
                    rs.getLong("id"),
                    rs.getString("aggregate_type"),
                    rs.getString("aggregate_id"),
                    rs.getString("event_type"),
                    rs.getString("payload"),
                    rs.getTimestamp("created_at").toInstant());
        }
    }
}
