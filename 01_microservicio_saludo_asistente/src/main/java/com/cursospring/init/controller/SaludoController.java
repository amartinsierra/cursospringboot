package com.cursospring.init.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cursospring.init.model.Ficha;

@RestController
public class SaludoController {
	@GetMapping(value="saludar",produces="text/plain")
	public String saludar() {
		return "Bienvenido a REST";
	}
	@GetMapping(value="saludar/{name}",produces="text/plain")
	public String saludar(@PathVariable String name) {
		return "Bienvenido a REST sr./a "+name;
	}
	@GetMapping(value="saludar/personal",produces="text/plain")
	public String saludar(String name, int age) {
		return "Bienvenido a REST sr./a "+name+" tienes "+age+" años";
	}
	@GetMapping(value="ficha",produces="application/json")
	public Ficha ficha(String name) {
		return new Ficha(name,"inventado@gmail.com",33);
	}
	@PostMapping(value="ficha",consumes="application/json")
	public void ficha(@RequestBody Ficha ficha) {
		System.out.println(ficha.getNombre());
	}
}
