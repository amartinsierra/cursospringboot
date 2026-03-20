package com.cursospring.init;

import java.util.List;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
	@Value("${mq.queue}")
	public  String queue;

    @Bean
    public Queue pedidosQueue() {
        return new Queue(queue);
    }
    @Bean
    public SimpleMessageConverter messageConverter() {
        SimpleMessageConverter converter = new SimpleMessageConverter();
        converter.setAllowedListPatterns(List.of("com.cursospring.init.model.*"));
        return converter;
    }

}
