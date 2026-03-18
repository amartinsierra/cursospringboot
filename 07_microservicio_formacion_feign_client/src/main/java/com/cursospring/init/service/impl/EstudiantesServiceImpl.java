package com.cursospring.init.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import com.cursospring.init.clients.AlumnosFeign;
import com.cursospring.init.dtos.EstudianteDto;
import com.cursospring.init.mappers.MapeadorEstudiante;
import com.cursospring.init.model.Alumno;
import com.cursospring.init.service.EstudiantesService;
@Service
public class EstudiantesServiceImpl implements EstudiantesService {
	@Value("${remote.url}")
	private String urlRemota;
	
	
	MapeadorEstudiante mapeadorEstudiante;
	AlumnosFeign alumnosFeign;

	

	public EstudiantesServiceImpl(MapeadorEstudiante mapeadorEstudiante,
			AlumnosFeign alumnosFeign) {
		
		this.mapeadorEstudiante = mapeadorEstudiante;
		this.alumnosFeign = alumnosFeign;
	}

	@Override
	public List<EstudianteDto> estudiantesPorCalificacion(double min, double max) {
		
		return alumnosFeign.alumnos().stream()
				.map(a->mapeadorEstudiante.alumnoToEstudiante(a))
				.filter(e->e.getCalificacion()>=min&&e.getCalificacion()<=max)
				.toList();
	}

	@Override
	public boolean altaEstudiante(EstudianteDto estudiante) {
		try {		
			alumnosFeign.altaAlumno(mapeadorEstudiante.estudianteToAlumno(estudiante));			
			return true;
		}catch(HttpClientErrorException ex) {
			if(ex.getStatusCode()==HttpStatus.CONFLICT) {
				return false;
			}
			throw ex;
		}
	}

}
