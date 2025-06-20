package nogrend.jobs.message;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @InjectMocks
    private MessageService subject;

    @Test
    void createMessage() {
        // given
        var content = "Hello there";

        // when
        var message = subject.create(content);

        // then
        System.out.println(message);
        assertThat(message.id()).isInstanceOf(MessageId.class);
        assertThat(message.id().id()).isInstanceOf(UUID.class);
        assertThat(message.content()).isEqualTo("Hello there");
        assertThat(message.createdAt()).isInstanceOf(Instant.class);
    }

    @Test
    void shouldNotCreateMessageWithEmptyContent() {
        // given
        var content = "";

        // when & then
        assertThatThrownBy(() -> subject.create(content))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Content cannot be null or empty");
    }

    @Test
    void shouldNotCreateMessageWithNullContent() {
        // given
        var content = (String) null;

        // when & then
        assertThatThrownBy(() -> subject.create(content))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Content cannot be null or empty");
    }

    @Test
    void sendMessage() {
        // given
        var message = new Message(new MessageId(), null, Instant.now());

        // when
         subject.sendMessage(message);

        // then

    }
}
