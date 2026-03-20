package com.cursospring.init.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cursospring.init.model.Pedido;

@Service
public class PedidosService {
	
	@Value("${mq.exchage}")
	private String exchange;
	@Value("${mq.key}")
	private String key;
	
	private RabbitTemplate rabbitTemplate;

	public PedidosService(RabbitTemplate rabbitTemplate) {
		
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void enviarPedido(Pedido pedido) {
		rabbitTemplate.convertAndSend(exchange, key, pedido);
	}
	
}
