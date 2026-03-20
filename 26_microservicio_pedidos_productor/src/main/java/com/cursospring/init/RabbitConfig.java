package com.cursospring.init;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
	@Value("${mq.queue}")
	private String PEDIDOS_QUEUE;
	@Value("${mq.exchage}")
	private String PEDIDOS_EXCHANGE;
	@Value("${mq.key}")
	private String ROUTING_KEY;
    @Bean
    Queue pedidosQueue() {
    	 return new Queue(PEDIDOS_QUEUE);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(PEDIDOS_EXCHANGE);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(ROUTING_KEY);
    }

}
