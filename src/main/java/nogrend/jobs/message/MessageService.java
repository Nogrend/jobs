package nogrend.jobs.message;

import org.springframework.stereotype.Service;

@Service
public class MessageService {

    public MessageService() {

    }

    public Message create(String content) {
        return Message.of(content);
    }

    public void sendMessage(Message message) {
        System.out.println("sending message: " + message);
    }
}
