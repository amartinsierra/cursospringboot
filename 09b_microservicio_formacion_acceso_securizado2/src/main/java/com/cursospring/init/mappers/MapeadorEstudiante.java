package com.cursospring.init.mappers;

import org.springframework.stereotype.Component;

import com.cursospring.init.dtos.EstudianteDto;
import com.cursospring.init.model.Alumno;

@Component
public class MapeadorEstudiante {
	public EstudianteDto alumnoToEstudiante(Alumno alumno) {
		return new EstudianteDto(alumno.getNombre(), alumno.getCurso(), alumno.getEmail(), alumno.getNota());
	}
	public Alumno estudianteToAlumno(EstudianteDto estudiante) {
		return new Alumno(estudiante.getNombre(), estudiante.getCurso(), estudiante.getEmail(), estudiante.getCalificacion());
	}
}
