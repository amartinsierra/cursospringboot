package com.cursospring.init.service.impl;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
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
	
	@Value("${remote.user}")
	private String user;
	@Value("${remote.password}")
	private String pass;
	
	RestClient restClient;
	MapeadorEstudiante mapeadorEstudiante;
	

	public EstudiantesServiceImpl(RestClient restClient, MapeadorEstudiante mapeadorEstudiante) {
		this.restClient = restClient;
		this.mapeadorEstudiante = mapeadorEstudiante;
	}

	@Override
	public List<EstudianteDto> estudiantesPorCalificacion(double min, double max) {
		return Arrays.stream(restClient.get()
				.uri(urlRemota)
				.header("Authorization", "Basic "+getBase64())
				.retrieve()
				.body(Alumno[].class)) //Stream<Alumno>
				.map(a->mapeadorEstudiante.alumnoToEstudiante(a))
				.filter(e->e.getCalificacion()>=min&&e.getCalificacion()<=max)
				.toList();
	}

	@Override
	public boolean altaEstudiante(EstudianteDto estudiante) {
		try {
			restClient.post()
			.uri(urlRemota)
			.header("Authorization", "Basic "+getBase64())
			.contentType(MediaType.APPLICATION_JSON)
			.body(mapeadorEstudiante.estudianteToAlumno(estudiante))
			.retrieve()
			.toBodilessEntity();
			return true;
		}catch(HttpClientErrorException ex) {
			if(ex.getStatusCode()==HttpStatus.CONFLICT) {
				return false;
			}
			throw ex;
		}
	}
	
	private String getBase64() {
		String cadena=user+":"+pass;
		return Base64.getEncoder().encodeToString(cadena.getBytes());
	}

}
