package com.cursospring.init.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cursospring.init.dtos.ItemDto;
import com.cursospring.init.mappers.MapeadorItem;
import com.cursospring.init.model.Item;
import com.cursospring.init.repository.ItemsRepository;
import com.cursospring.init.service.ItemsService;
@Service
public class ItemsServiceImpl implements ItemsService {
	
	//@Autowired
	ItemsRepository itemsRepository;
	MapeadorItem mapeadorItem;
	public ItemsServiceImpl(ItemsRepository itemsRepository,MapeadorItem mapeadorItem) {
		this.itemsRepository = itemsRepository;
		this.mapeadorItem=mapeadorItem;
	}

	@Override
	public List<ItemDto> buscarItems(String tematica) {
		return itemsRepository.findByTematica(tematica) //List<Item>
				.stream() //Stream<Item>
				.map(i->mapeadorItem.itemEntityToDto(i)) //Stream<ItemDto>
				.toList();
	}

	@Override
	public ItemDto eliminarItem(String url) {
		Optional<Item> itemOp=itemsRepository.findFirstByUrl(url);
		if(itemOp.isPresent()) {
			itemsRepository.deleteByUrl(url);
			return mapeadorItem.itemEntityToDto(itemOp.get());
		}
		return new ItemDto();
		
	}

	@Override
	public boolean nuevoItem(ItemDto item) {
		if(!itemsRepository.existsByUrlAndTematica(item.getUrl(), item.getTematica())) {
			itemsRepository.save(mapeadorItem.itemDtoToEntity(item));
			return true;
		}
		return false;
	}

}
