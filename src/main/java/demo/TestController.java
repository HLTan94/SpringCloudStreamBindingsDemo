package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    StreamService streamService;

    @GetMapping(value = "/dummy")
    public String dummyController() {
        streamService.dummyPublisher();
        return "Testing done.";
    }

}
