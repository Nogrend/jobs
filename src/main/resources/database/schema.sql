CREATE TABLE shedlock
(
    name       VARCHAR(64),
    lock_until TIMESTAMP(3) NULL,
    locked_at  TIMESTAMP(3) NULL,
    locked_by  VARCHAR(255),
    PRIMARY KEY (name)
);

-- Create the counter table if it doesn't exist
CREATE TABLE IF NOT EXISTS counter (
                                       id VARCHAR(50) PRIMARY KEY,
                                       count BIGINT NOT NULL,
                                       last_updated TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Insert the initial counter record with value 0 if it doesn't exist
-- Using ON CONFLICT DO NOTHING to prevent duplicates
INSERT INTO counter (id, count)
VALUES ('main_counter', 0)
ON CONFLICT (id) DO NOTHING;

CREATE TABLE outbox (
                        id BIGSERIAL PRIMARY KEY ,
                        aggregate_type VARCHAR(255) NOT NULL,
                        aggregate_id VARCHAR(255) NOT NULL,
                        event_type VARCHAR(255) NOT NULL,
                        payload TEXT NOT NULL,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        processed_at TIMESTAMP NULL,
                        status VARCHAR(50) DEFAULT 'PENDING'
);

CREATE INDEX idx_outbox_status ON outbox(status);