package ftn.isa;

import ftn.isa.service.ContractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class Consumer1 {

    @Autowired
    private ContractService contractService;

    private static final Logger log = LoggerFactory.getLogger(Consumer1.class);
    /*
     * @RabbitListener anotira metode za kreiranje handlera za bilo koju poruku koja pristize,
     * sto znaci da ce se kreirati listener koji je konektovan na RabbitQM queue i koji ce
     * prosledjivati poruke metodi. Listener ce konvertovati poruku u odgovorajuci tip koristeci
     * odgovarajuci konvertor poruka (implementacija org.springframework.amqp.support.converter.MessageConverter interfejsa).
     */
    @RabbitListener(queues="glavna")
    public void handler(String message){
        contractService.saveFromString(message);
        log.info("Dobio sam ovo> " + message);
    }
}

