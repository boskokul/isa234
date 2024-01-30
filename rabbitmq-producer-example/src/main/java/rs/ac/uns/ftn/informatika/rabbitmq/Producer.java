package rs.ac.uns.ftn.informatika.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
public class Producer {
	
	private static final Logger log = LoggerFactory.getLogger(Producer.class);
	
	/*
	 * RabbitTemplate je pomocna klasa koja uproscava sinhronizovani
	 * pristup RabbitMQ za slanje i primanje poruka.
	 */
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	/*
	 * U ovom slucaju routingKey ce biti ime queue.
	 * Poruka se salje u exchange (default exchange u ovom primeru) i
	 * exchange ce rutirati poruke u pravi queue.
	 */
	public void sendTo(String routingkey, String message){
		log.info("Sending> ... Message=[ " + message + " ] RoutingKey=[" + routingkey + "]");
		this.rabbitTemplate.convertAndSend(routingkey, message);
	}

	@Scheduled(cron = "0 0 0/24 * * *")
	public void sendContracts() throws InterruptedException {
		String message1 = "{\"hospitalName\": \"Medi-group\",\"duration\": 24,\"dayOfMonth\": 14,\"hours\": 17,\"minutes\": 35,\"status\": \"NotDelivered\",\"contractEquipment\": [{\"name\": \"oprema1\",\"quantity\": 12}]}";
		String message2 = "{\"hospitalName\": \"Adzi-Badem\",\"duration\": 5,\"dayOfMonth\": 10,\"hours\": 13,\"minutes\": 20,\"status\": \"NotDelivered\",\"contractEquipment\": [{\"name\": \"oprema2\",\"quantity\": 4}]}";
		String message3 = "{\"hospitalName\": \"New-hospital\",\"duration\": 10,\"dayOfMonth\": 22,\"hours\": 20,\"minutes\": 11,\"status\": \"NotDelivered\",\"contractEquipment\": [{\"name\": \"oprema1\",\"quantity\": 5}]}";
		String routingkey = "glavna";
		sendTo(routingkey, message1);
		Thread.sleep(500);
		sendTo(routingkey, message2);
		Thread.sleep(500);
		sendTo(routingkey, message3);
	}

	@PostConstruct
	public void sendssss() throws InterruptedException {
		String message1 = "{\"hospitalName\": \"Medi-group\",\"duration\": 24,\"dayOfMonth\": 30,\"hours\": 0,\"minutes\": 55,\"status\": \"NotDelivered\",\"contractEquipment\": [{\"name\": \"oprema1\",\"quantity\": 12}]}";
		String message2 = "{\"hospitalName\": \"Adzi-Badem\",\"duration\": 5,\"dayOfMonth\": 30,\"hours\": 0,\"minutes\": 58,\"status\": \"NotDelivered\",\"contractEquipment\": [{\"name\": \"oprema2\",\"quantity\": 4}]}";
		String message3 = "{\"hospitalName\": \"New-hospital\",\"duration\": 10,\"dayOfMonth\": 30,\"hours\": 0,\"minutes\": 59,\"status\": \"NotDelivered\",\"contractEquipment\": [{\"name\": \"oprema1\",\"quantity\": 5}]}";
		String routingkey = "glavna";
		sendTo(routingkey, message1);
		Thread.sleep(500);
		sendTo(routingkey, message2);
		Thread.sleep(500);
		sendTo(routingkey, message3);
	}
}
