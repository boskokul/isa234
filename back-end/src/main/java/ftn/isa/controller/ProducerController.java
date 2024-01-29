package ftn.isa.controller;

import ftn.isa.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api")
public class ProducerController {

    @Autowired
    private Producer producer;

    @PostMapping(value="/{queue}", consumes = "text/plain")
    public ResponseEntity<String> sendMessage(@PathVariable("queue") String queue, @RequestBody String message) {
        producer.sendTo(queue, message);
        return ResponseEntity.ok().build();
    }

}

