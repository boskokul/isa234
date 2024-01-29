package rs.ac.uns.ftn.isa.kafkaconsumerexample.component;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    public void listen(String message) throws InterruptedException {
        coordinates.clear();
        coordinates.add("45.26412170665416,19.830374334447974");
        calculateIntermediaryPositions(6);
        coordinates.add("45.23984389699702,19.843656098804175");
        for(String s: coordinates){
            produce(s);
            TimeUnit.SECONDS.sleep(Integer.parseInt(message));
        }
    }
    private void calculateIntermediaryPositions(double count){
        for (int i = 1; i <= count; i++) {
            double t = i / count;
            double latitude = (1 - t) * 45.26412170665416 + t * 45.23984389699702;
            double longitude = (1 - t) * 19.830374334447974 + t * 19.843656098804175;
            coordinates.add(Double.toString(latitude)+","+Double.toString(longitude));
        }
    }
    public void produce(String message) {
        template.send("test-topic1", message);
    }
    public List<String> getMessages() {
        return messages;
    }

}