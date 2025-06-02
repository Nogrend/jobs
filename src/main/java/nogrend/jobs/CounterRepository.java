package nogrend.jobs;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Optional;

@Repository
public class CounterRepository {

    private final JdbcTemplate jdbcTemplate;

    public CounterRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Optional<Long> getCounter(String counterId) {
        String sql = "SELECT count FROM counter WHERE id = ?";
            return Objects.requireNonNull(jdbcTemplate.queryForObject(sql, Long.class, counterId)).describeConstable();
    }

    public void incrementCounter(String counterId) {
        String sql = "UPDATE counter SET count = count+1 WHERE id = ?";
        jdbcTemplate.update(sql, counterId);
    }

}
