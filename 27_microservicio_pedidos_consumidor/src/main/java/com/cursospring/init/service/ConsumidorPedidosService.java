package com.cursospring.init.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.cursospring.init.model.Pedido;

@Service
public class ConsumidorPedidosService {
	
	@RabbitListener(queues = "${mq.queue}")
	public void consumirPedido(Pedido pedido) {
		System.out.println(pedido);
	}
}
