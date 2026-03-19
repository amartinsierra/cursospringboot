package com.cursospring.init.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cursospring.init.dtos.ItemDto;
import com.cursospring.init.service.ItemsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
	@Operation(summary = "Lista de resultados por temática",
			description="A partir de la temática, nos devuelve la lista de resultados asociados")
	@GetMapping()
	public ResponseEntity<List<ItemDto>> itemsPorTematica(@Parameter(description = "Nombre de la temática de búsqueda") @NotBlank @RequestParam String tematica){
		return ResponseEntity.ok(itemsService.buscarItems(tematica));
	}
	@DeleteMapping()
	public ResponseEntity<ItemDto> eliminarPorUrl(@RequestParam String url) {
		return ResponseEntity.ok(itemsService.eliminarItem(url));
	}
	@ApiResponses({
		@ApiResponse(responseCode = "201",description = "El item se ha añadido correctamente"),
		@ApiResponse(responseCode = "409",description = "El item no se ha añadido porque está duplicado")
	})
	@PostMapping()
	public ResponseEntity<Void> altaItem(@Valid @RequestBody ItemDto item) {
		if(itemsService.nuevoItem(item)) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
}
