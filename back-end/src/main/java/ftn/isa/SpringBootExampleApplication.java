package ftn.isa;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@EnableScheduling
@SpringBootApplication
@EnableTransactionManagement
public class SpringBootExampleApplication {
	@Bean
	public Validator validator() {
		ValidatorFactory validatorFactory = Validation.byDefaultProvider().configure().buildValidatorFactory();
		return validatorFactory.getValidator();
	}

	@Value("${myqueue}")
	String queue;




	@Bean
	Queue queue() {
		return new Queue(queue, true);
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	/*
	 * Registrujemo bean koji ce sluziti za konekciju na RabbitMQ
	 * gde se mi u primeru kacimo u lokalu.
	 */
	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
		return connectionFactory;
	}
	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = SpringApplication.run(SpringBootExampleApplication.class, args);

	}

}
