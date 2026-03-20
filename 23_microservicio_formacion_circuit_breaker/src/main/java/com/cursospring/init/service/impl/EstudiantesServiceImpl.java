package com.cursospring.init.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import com.cursospring.init.dtos.EstudianteDto;
import com.cursospring.init.mappers.MapeadorEstudiante;
import com.cursospring.init.model.Alumno;
import com.cursospring.init.service.EstudiantesService;
@Service
public class EstudiantesServiceImpl implements EstudiantesService {
	@Value("${remote.url}")
	private String urlRemota;
	
	RestClient restClient;
	MapeadorEstudiante mapeadorEstudiante;
	
	
	CircuitBreakerFactory factory;
	
	public EstudiantesServiceImpl(RestClient restClient, MapeadorEstudiante mapeadorEstudiante,
			CircuitBreakerFactory factory) {
		super();
		this.restClient = restClient;
		this.mapeadorEstudiante = mapeadorEstudiante;
		this.factory = factory;
	}

	@Override
	public List<EstudianteDto> estudiantesPorCalificacion(double min, double max) {
		CircuitBreaker cb=factory.create("main");
		
		return cb.run(()->{
			return Arrays.stream(restClient.get()
					.uri(urlRemota)
					.retrieve()
					.body(Alumno[].class)) //Stream<Alumno>
					.map(a->mapeadorEstudiante.alumnoToEstudiante(a))
					.filter(e->e.getCalificacion()>=min&&e.getCalificacion()<=max)
					.toList();
		}, ex->List.of());
		
	}

	@Override
	public boolean altaEstudiante(EstudianteDto estudiante) {
		CircuitBreaker cb=factory.create("main");
		
		return cb.run(()->{
			restClient.post()
			.uri(urlRemota)
			.contentType(MediaType.APPLICATION_JSON)
			.body(mapeadorEstudiante.estudianteToAlumno(estudiante))
			.retrieve()
			.toBodilessEntity();
			return true;
		},ex->{
			return false;
		});
		
		
		
	}

}
