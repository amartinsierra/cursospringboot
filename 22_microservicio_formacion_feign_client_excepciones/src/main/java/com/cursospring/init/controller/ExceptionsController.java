package com.cursospring.init.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import feign.FeignException;

@RestControllerAdvice
public class ExceptionsController {
	@ExceptionHandler(FeignException.class)
	public ResponseEntity<Void> errorComunicacion(FeignException ex){
		if(ex.status()==409) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		ex.printStackTrace();
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
	}
}
