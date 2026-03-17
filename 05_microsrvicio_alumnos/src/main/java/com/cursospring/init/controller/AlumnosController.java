package com.cursospring.init.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cursospring.init.dtos.AlumnoDto;
import com.cursospring.init.service.AlumnosService;

@RequestMapping("alumnos")
@RestController
public class AlumnosController {
	
	AlumnosService alumnosService;
	
	public AlumnosController(AlumnosService alumnosService) {
		this.alumnosService = alumnosService;
	}
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AlumnoDto> alta(@RequestBody AlumnoDto alumno) {
		Optional<AlumnoDto> alumnoOp= alumnosService.altaAlumno(alumno);
		if(alumnoOp.isPresent()) {
			return new ResponseEntity<>(alumnoOp.get(),HttpStatus.CREATED);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
	@GetMapping(value="cursos",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> cursos() {
		return  new ResponseEntity<>(alumnosService.cursos(),HttpStatus.OK);	
	}
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AlumnoDto>> alumnos() {
		return new ResponseEntity<>(alumnosService.alumnos(),HttpStatus.OK);
		
	}
	@GetMapping(value="por-curso",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AlumnoDto>> alumnoscurso(@RequestParam String curso) {
		return new ResponseEntity<>(alumnosService.alumnosCurso(curso),HttpStatus.OK);
	}
	@DeleteMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<Void> eliminar(@RequestParam String email){
		alumnosService.eliminarAlumno(email);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
