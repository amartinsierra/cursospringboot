package com.cursospring.init.service;

import java.util.List;

import com.cursospring.init.model.Item;

public interface ItemsService {
	List<Item> buscarItems(String tematica);
	Item eliminarItem(String url);
	boolean nuevoItem(Item item);
}
