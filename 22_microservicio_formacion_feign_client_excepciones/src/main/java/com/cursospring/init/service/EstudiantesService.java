package com.cursospring.init.service;

import java.util.List;

import com.cursospring.init.dtos.EstudianteDto;

public interface EstudiantesService {
	List<EstudianteDto> estudiantesPorCalificacion(double min, double max);
	void altaEstudiante(EstudianteDto estudiante);
}
