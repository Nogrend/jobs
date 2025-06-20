package nogrend.jobs.message;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public record Message(
        MessageId id,
        String content,
        Instant createdAt
) {
    public Message {
        content = Objects.requireNonNull(content, "Content cannot be null");
        if (content.isBlank()) throw new IllegalArgumentException();
    }

    public static Message of(String content) {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Content cannot be null or empty");
        }
        return new Message(new MessageId(), content, Instant.now());
    }

    @Override
    public String toString() {
        String formattedTime = DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault())
                .format(createdAt);

        return String.format(
                "Message[id=%s, content='%s', createdAt=%s]",
                id != null ? id : "N/A",
                content,
                formattedTime
        );
    }
}
