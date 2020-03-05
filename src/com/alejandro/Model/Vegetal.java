package com.alejandro.Model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Vegetal {

		private int id;
	@NotEmpty(message="Campo obligatorio")
	@NotNull
		private String nombre;
	@NotEmpty(message="Campo obligatorio")
	@NotNull
		private String categoria;
	@NotEmpty(message="Campo obligatorio")
	@NotNull
		private String procedencia;
	@NotEmpty(message="Campo obligatorio")
	@NotNull
		private String imagen;
	@NotNull
		private float precio;
	
public Vegetal() {
	
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getCategoria() {
	return categoria;
}
public void setCategoria(String categoria) {
	this.categoria = categoria;
}
public String getImagen() {
	return imagen;
}
public void setImagen(String imagen) {
	this.imagen = imagen;
}
public float getPrecio() {
	return precio;
}
public void setPrecio(float precio) {
	this.precio = precio;
}
public String getProcedencia() {
	return procedencia;
}
public void setProcedencia(String procedencia) {
	this.procedencia = procedencia;
}
public Vegetal(int id, String nombre, String categoria, String imagen, float precio, String procedencia) {
	super();
	this.id = id;
	this.nombre = nombre;
	this.categoria = categoria;
	this.imagen = imagen;
	this.precio = precio;
	this.procedencia = procedencia;
}


}
