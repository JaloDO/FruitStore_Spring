package com.alejandro.Model;

public class Vegetal {
private int id;
private String nombre;
private String categoria;
private String procedencia;
private String imagen;
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
