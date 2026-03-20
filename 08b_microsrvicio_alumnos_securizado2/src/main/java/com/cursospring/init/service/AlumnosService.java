package com.cursospring.init.service;

import java.util.List;
import java.util.Optional;

import com.cursospring.init.dtos.AlumnoDto;

public interface AlumnosService {
	Optional<AlumnoDto> altaAlumno(AlumnoDto alumno);
	List<AlumnoDto> alumnos();
	
	List<String> cursos();
	List<AlumnoDto> alumnosCurso(String curso);
	void eliminarAlumno(String email);
}
