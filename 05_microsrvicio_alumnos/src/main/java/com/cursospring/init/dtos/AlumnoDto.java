package com.cursospring.init.dtos;

import com.cursospring.init.model.Alumno;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlumnoDto {
	private int idAlumno;
	private String nombre;
	private String curso;
	private String email;
	private double nota;
}
