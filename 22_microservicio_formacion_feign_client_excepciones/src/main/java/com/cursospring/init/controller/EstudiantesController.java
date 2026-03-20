package com.cursospring.init.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursospring.init.dtos.EstudianteDto;
import com.cursospring.init.service.EstudiantesService;
@RequestMapping("estudiantes")
@RestController
public class EstudiantesController {
	
	EstudiantesService estudiantesService;

	public EstudiantesController(EstudiantesService estudiantesService) {
		this.estudiantesService = estudiantesService;
	}
	@GetMapping()
	public ResponseEntity<List<EstudianteDto>> estudiantesPorCalificacion(double min, double max){
		return ResponseEntity.ok(estudiantesService.estudiantesPorCalificacion(min, max));
	}
	
	@PostMapping()
	public ResponseEntity<Void> altaEstudiante(@RequestBody EstudianteDto estudiante){
		estudiantesService.altaEstudiante(estudiante);
		return ResponseEntity.ok().build();
		
	}
}
