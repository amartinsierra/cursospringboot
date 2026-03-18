package com.cursospring.init.service.impl;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import com.cursospring.init.dtos.EstudianteDto;
import com.cursospring.init.dtos.TokenDto;
import com.cursospring.init.mappers.MapeadorEstudiante;
import com.cursospring.init.model.Alumno;
import com.cursospring.init.service.EstudiantesService;
@Service
public class EstudiantesServiceImpl implements EstudiantesService,InitializingBean {
	@Value("${remote.url}")
	private String urlRemota;
	
	@Value("${remote.user}")
	private String user;
	@Value("${remote.password}")
	private String pass;
	
	@Value("${oauth2.url}")
	private String urlOauth2;
	@Value("${oauth2.clientId}")
	private String clientId;
	@Value("${oauth2.grantType}")
	private String grantType;
	
	TokenDto tokenDto;
	
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
				.header("Authorization", "Bearer "+tokenDto.getAccess_token())
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
			.header("Authorization", "Bearer "+tokenDto.getAccess_token())
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
	
	

	@Override
	public void afterPropertiesSet() throws Exception {
		obtenerToken();
		System.out.println(tokenDto.getAccess_token());
	}
	private void obtenerToken() {
		MultiValueMap<String, String> params=new LinkedMultiValueMap<>();
		params.add("client_id", clientId);
		params.add("username", user);
		params.add("password", pass);
		params.add("grant_type", grantType);
		tokenDto=restClient.post()
		.uri(urlOauth2)
		.contentType(MediaType.APPLICATION_FORM_URLENCODED)
		.body(params)
		.retrieve()
		.body(TokenDto.class);
	}

}
