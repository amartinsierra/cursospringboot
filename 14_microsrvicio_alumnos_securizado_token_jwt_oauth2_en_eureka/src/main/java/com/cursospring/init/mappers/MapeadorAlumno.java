package com.cursospring.init.mappers;

import org.springframework.stereotype.Component;

import com.cursospring.init.dtos.AlumnoDto;
import com.cursospring.init.model.Alumno;

@Component
public class MapeadorAlumno {
	public AlumnoDto alumnoEntityToDto(Alumno alumno) {
		return new AlumnoDto(alumno.getIdAlumno(), alumno.getNombre(),alumno.getCurso(),alumno.getEmail(),alumno.getNota());
	}
	
	public Alumno alumnoDtoToEntity(AlumnoDto alumno) {
		return new Alumno(alumno.getIdAlumno(), alumno.getNombre(),alumno.getCurso(),alumno.getEmail(),alumno.getNota());
	}
}
