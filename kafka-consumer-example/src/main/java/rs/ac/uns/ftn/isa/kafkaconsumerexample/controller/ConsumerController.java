package rs.ac.uns.ftn.isa.kafkaconsumerexample.controller;
import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import rs.ac.uns.ftn.isa.kafkaconsumerexample.component.Consumer;

@RestController
@RequestMapping("/api/consumer")
public class ConsumerController {
    private Consumer myTopicConsumer;
    private KafkaTemplate<String, String> template;

    public ConsumerController(Consumer myTopicConsumer, KafkaTemplate<String, String> template) {
        this.myTopicConsumer = myTopicConsumer;
        this.template = template;
    }

    @GetMapping("/get")
    public List<String> getMessages() {
        return myTopicConsumer.getMessages();
    }

    @PostMapping("/send")
    public void produce(@RequestBody String message) {
        template.send("test-topic1", message);
    }

}