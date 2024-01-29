package ftn.isa.controller;

import ftn.isa.util.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/positionsimulator")
public class PositionSimulatorController {

    private Consumer myTopicConsumer;
    private KafkaTemplate<String, String> template;
    @Autowired

    private SimpMessagingTemplate simpMessagingTemplate;

    public PositionSimulatorController(Consumer myTopicConsumer, KafkaTemplate<String, String> template) {
        this.myTopicConsumer = myTopicConsumer;
        this.template = template;
    }
    //uzima podatke od simulatora
    @GetMapping("/get")
    public List<String> getMessages() {
        broadcastNotification();
        return myTopicConsumer.getMessages();
    }
    //salje koordinate frontendu
    @MessageMapping("/send/message")
    public String broadcastNotification() {
        this.simpMessagingTemplate.convertAndSend("/socket-publisher", "koordinate");
        return "poslato";
    }
    //aktivira simulator
    @PostMapping("/send")
    public void produce(@RequestBody String message) {
        template.send("test-topic", message);
    }

}
