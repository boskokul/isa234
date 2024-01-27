package ftn.isa.controller;

import ftn.isa.util.Consumer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/positionsimulator")
public class PositionSimulatorController {

    private Consumer myTopicConsumer;
    private KafkaTemplate<String, String> template;

    public PositionSimulatorController(Consumer myTopicConsumer, KafkaTemplate<String, String> template) {
        this.myTopicConsumer = myTopicConsumer;
        this.template = template;
    }

    @GetMapping("/get")
    public List<String> getMessages() {
        return myTopicConsumer.getMessages();
    }

    @PostMapping("/send")
    public void produce(@RequestBody String message) {
        template.send("test-topic", message);
    }

}
