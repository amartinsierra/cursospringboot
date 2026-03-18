package com.cursospring.init.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cursospring.init.model.Alumno;
@FeignClient(name="alumnosFeing",url = "${remote.url}")
public interface AlumnosFeign {
	@GetMapping()
	List<Alumno> alumnos();
	@PostMapping()
	void altaAlumno(@RequestBody Alumno alumno);
}
