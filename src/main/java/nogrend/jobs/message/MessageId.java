package nogrend.jobs.message;

import org.springframework.util.Assert;

import java.util.UUID;

public record MessageId(
        UUID id
) {
    public MessageId {
        Assert.notNull(id, "id must not be null");
    }

    public MessageId(){
        this(UUID.randomUUID());
    }
}
