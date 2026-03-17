package com.cursospring.init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cursospring.init.model.Item;
import com.cursospring.init.service.ItemsService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("items")
public class ItemsController {
	//@Autowired
	ItemsService itemsService;	
	public ItemsController(ItemsService itemsService) {
		this.itemsService = itemsService;
	}
	@GetMapping()
	public ResponseEntity<List<Item>> itemsPorTematica(@NotBlank @RequestParam String tematica){
		return ResponseEntity.ok(itemsService.buscarItems(tematica));
	}
	@DeleteMapping()
	public ResponseEntity<Item> eliminarPorUrl(@RequestParam String url) {
		return ResponseEntity.ok(itemsService.eliminarItem(url));
	}
	@PostMapping()
	public ResponseEntity<Void> altaItem(@Valid @RequestBody Item item) {
		if(itemsService.nuevoItem(item)) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
}
