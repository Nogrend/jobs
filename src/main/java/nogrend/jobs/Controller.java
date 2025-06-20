package nogrend.jobs;

import nogrend.jobs.outbox.OutboxMessage;
import nogrend.jobs.outbox.OutboxService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/peek")
public class Controller {

    private final OutboxService outboxService;

    public Controller(OutboxService outboxService) {
        this.outboxService = outboxService;
    }

    @GetMapping
    public List<OutboxMessage> peek(@RequestParam int limit) {
        return this.outboxService.getByLimit(limit);
    }

    @PostMapping
    public void receiveMessage(@RequestBody MessageRequest request) {
        outboxService.save(request.id(), request.message());
    }
}
