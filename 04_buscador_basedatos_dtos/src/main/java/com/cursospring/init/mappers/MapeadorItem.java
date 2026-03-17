package com.cursospring.init.mappers;

import org.springframework.stereotype.Component;

import com.cursospring.init.dtos.ItemDto;
import com.cursospring.init.model.Item;

@Component
public class MapeadorItem {
	public ItemDto itemEntityToDto(Item item) {
		return new ItemDto(item.getUrl(), item.getTematica(), item.getDescripcion());
	}
	
	public Item itemDtoToEntity(ItemDto item) {
		return new Item(item.getUrl(), item.getTematica(), item.getDescripcion());
	}
}
