package com.cursospring.init.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cursospring.init.dtos.AlumnoDto;
import com.cursospring.init.mappers.MapeadorAlumno;
import com.cursospring.init.model.Alumno;
import com.cursospring.init.repository.AlumnosRepository;

@Service
public class AlumnosServiceImpl implements AlumnosService {

	
	AlumnosRepository alumnosRepository;
	MapeadorAlumno mapeadorAlumno;

	public AlumnosServiceImpl(AlumnosRepository alumnosRepository, MapeadorAlumno mapeadorAlumno) {
		this.alumnosRepository = alumnosRepository;
		this.mapeadorAlumno = mapeadorAlumno;
	}

	@Override
	public Optional<AlumnoDto> altaAlumno(AlumnoDto alumno) {
		if(alumnosRepository.findFirstByEmailAndCurso(alumno.getEmail(), alumno.getCurso()).isEmpty()) {
			Alumno alumnoOp=alumnosRepository.save(mapeadorAlumno.alumnoDtoToEntity(alumno));
			return Optional.of(mapeadorAlumno.alumnoEntityToDto(alumnoOp));
		}
		return Optional.empty();
	}

	@Override
	public List<String> cursos() {
		return alumnosRepository.findAllCursos();
	}

	@Override
	public List<AlumnoDto> alumnosCurso(String curso) {
		return alumnosRepository.findByCurso(curso).stream()
				.map(mapeadorAlumno::alumnoEntityToDto)
				.toList();
	}

	@Override
	public void eliminarAlumno(String email) {
		Optional<Alumno> alumno=alumnosRepository.findFirstByEmail(email);
		if(alumno.isPresent()) {
			alumnosRepository.deleteByEmail(email);
			
		}
		
		
	}

	@Override
	public List<AlumnoDto> alumnos() {
		return alumnosRepository.findAll().stream()
				.map(mapeadorAlumno::alumnoEntityToDto)
				.toList();
	}

}
