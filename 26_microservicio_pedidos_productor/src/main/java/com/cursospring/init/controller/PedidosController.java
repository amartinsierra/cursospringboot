package com.cursospring.init.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cursospring.init.model.Pedido;
import com.cursospring.init.service.PedidosService;

@RestController
public class PedidosController {
	private PedidosService pedidoService;

	public PedidosController(PedidosService pedidoService) {
		this.pedidoService = pedidoService;
	}
	@PostMapping("pedido")
	public ResponseEntity<Void> enviarPedido(@RequestBody Pedido pedido){
		pedidoService.enviarPedido(pedido);
		return ResponseEntity.ok().build();
	}
}
