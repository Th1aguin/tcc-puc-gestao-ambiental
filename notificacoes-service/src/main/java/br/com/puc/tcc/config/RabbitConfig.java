package br.com.puc.tcc.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitConfig {

	@Value("${queue.order.name}")
    private String orderQueue;
	
	@Bean
    public Queue queue() {
        return new Queue(orderQueue, true);
    }
}
