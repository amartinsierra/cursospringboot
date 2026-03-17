package com.cursospring.init.service;

import java.util.List;

import com.cursospring.init.dtos.ItemDto;

public interface ItemsService {
	List<ItemDto> buscarItems(String tematica);
	ItemDto eliminarItem(String url);
	boolean nuevoItem(ItemDto item);
}
