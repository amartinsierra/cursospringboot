package com.cursospring.init.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cursospring.init.model.Item;
import com.cursospring.init.service.ItemsService;
@Service
public class ItemsServiceImpl implements ItemsService {
	static List<Item> direcciones=new ArrayList<>(Arrays.asList(new Item("http://www.amazon.es","libros","web de libros y más cosas"),
			new Item("http://www.fnac.es","libros","libreria completa"),
			new Item("http://www.travel.es","viajes","viajes por el mundo"),
			new Item("http://www.game.es","juegos","el mundo del juego"),
			new Item("http://www.fly.com","viajes","vuelos a todos los destinos"),
			new Item("http://www.casadellibro.es","libros","libros de todos los temas")
			));

	@Override
	public List<Item> buscarItems(String tematica) {
		return direcciones.stream()
				.filter(item -> item.getTematica().equalsIgnoreCase(tematica))
				.toList();
	}

	@Override
	public Item eliminarItem(String url) {
		Optional<Item> opitem=direcciones.stream()
				.filter(item -> item.getUrl().equalsIgnoreCase(url))
				.findFirst();
		if(opitem.isPresent()) {
			direcciones.removeIf(item -> item.getUrl().equalsIgnoreCase(url));
			return opitem.get();
		}
		return new Item();
	}

	@Override
	public boolean nuevoItem(Item item) {
		if(direcciones.stream().noneMatch(i->i.getUrl().equals(item.getUrl())&&i.getTematica().equals(item.getTematica()))) {
			return direcciones.add(item);
		}
		return false;
	}

}
