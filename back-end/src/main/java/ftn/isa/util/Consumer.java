package ftn.isa.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Consumer {

    private final List<String> messages = new ArrayList<>();
    /*
     * @KafkaListener anotacijom se definiše naziv topic-a iz koje anotirana metoda
     * osluškuje poruke.
     */
    @Autowired

    private SimpMessagingTemplate simpMessagingTemplate;

    @KafkaListener(topics = "test-topic1", groupId = "kafka-sandbox")
    public void listen(String message) {
        broadcastNotification(message);
    }

    @MessageMapping("/send/message")
    public String broadcastNotification(String coordinate) {
        this.simpMessagingTemplate.convertAndSend("/socket-publisher", coordinate);
        return "poslato";
    }
    public List<String> getMessages() {
        return messages;
    }

}