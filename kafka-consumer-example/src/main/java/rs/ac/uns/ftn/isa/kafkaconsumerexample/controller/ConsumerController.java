package rs.ac.uns.ftn.isa.kafkaconsumerexample.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import rs.ac.uns.ftn.isa.kafkaconsumerexample.component.Consumer;

@RestController
@RequestMapping("/api/consumer")
public class ConsumerController {
    private Consumer myTopicConsumer;
    private KafkaTemplate<String, String> template;
    private List<String> coordinates = new ArrayList<>();
    @KafkaListener(topics = "test-topic", groupId = "kafka-sandbox")
    public void listen() {
        coordinates.clear();
        coordinates.add("15.521, 34.5135");
        coordinates.add("16.521, 35.5135");
        coordinates.add("16.521, 36.5135");
        for(String s: coordinates){
            produce(s);
        }
    }
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