package nogrend.jobs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/peek")
public class Controller {

    @GetMapping
    public String peek() {
        return "another peek";
    }
}
