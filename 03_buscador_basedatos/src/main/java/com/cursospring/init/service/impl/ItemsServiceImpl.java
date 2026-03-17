package com.cursospring.init.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursospring.init.model.Item;
import com.cursospring.init.repository.ItemsRepository;
import com.cursospring.init.service.ItemsService;
@Service
public class ItemsServiceImpl implements ItemsService {
	
	//@Autowired
	ItemsRepository itemsRepository;
	public ItemsServiceImpl(ItemsRepository itemsRepository) {
		this.itemsRepository = itemsRepository;
	}

	@Override
	public List<Item> buscarItems(String tematica) {
		return itemsRepository.findByTematica(tematica);
	}

	@Override
	public Item eliminarItem(String url) {
		Optional<Item> itemOp=itemsRepository.findFirstByUrl(url);
		if(itemOp.isPresent()) {
			itemsRepository.deleteByUrl(url);
			return itemOp.get();
		}
		return new Item();
		
	}

	@Override
	public boolean nuevoItem(Item item) {
		if(!itemsRepository.existsByUrlAndTematica(item.getUrl(), item.getTematica())) {
			itemsRepository.save(item);
			return true;
		}
		return false;
	}

}
