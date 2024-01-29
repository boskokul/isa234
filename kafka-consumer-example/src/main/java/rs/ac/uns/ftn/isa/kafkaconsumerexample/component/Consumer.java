package rs.ac.uns.ftn.isa.kafkaconsumerexample.component;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Component
public class Consumer {

    private final List<String> messages = new ArrayList<>();
    private KafkaTemplate<String, String> template;
    private List<String> coordinates = new ArrayList<>();
    /*
     * @KafkaListener anotacijom se definiše naziv topic-a iz koje anotirana metoda
     * osluškuje poruke.
     */
    public Consumer(KafkaTemplate<String, String> template) {
        this.template = template;
    }
    @KafkaListener(topics = "test-topic", groupId = "kafka-sandbox")
    public void listen(String message) {
        coordinates.clear();
        coordinates.add("15.521, 34.5135");
        coordinates.add("16.521, 35.5135");
        coordinates.add("16.521, 36.5135");
        for(String s: coordinates){
            produce(s);
        }
        synchronized (messages) {
            messages.add(message);
        }
    }
    public void produce(String message) {
        template.send("test-topic1", message);
    }
    public List<String> getMessages() {
        return messages;
    }

}