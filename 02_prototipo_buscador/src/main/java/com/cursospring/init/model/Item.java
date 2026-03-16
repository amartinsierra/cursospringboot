package com.cursospring.init.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Item {
	@NotBlank
	private String url;
	private String tematica;
	@Size(min = 10)
	private String descripcion;
	public Item(String url, String tematica, String descripcion) {
		this.url = url;
		this.tematica = tematica;
		this.descripcion = descripcion;
	}
	public Item() {
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTematica() {
		return tematica;
	}
	public void setTematica(String tematica) {
		this.tematica = tematica;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
