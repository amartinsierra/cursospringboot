package com.cursospring.init.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pedido implements Serializable{
	private int codPedido;
	private String producto;
	private double precio;
	private String observaciones;
}
