package com.alejandro.Model;

public class Slide {
	private int id;
	 private String texto;
	 private String imagen;
 public Slide() {
		
	}

public Slide(int id, String texto, String imagen) {
	super();
	this.id = id;
	this.texto = texto;
	this.imagen = imagen;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getTexto() {
	return texto;
}
public void setTexto(String texto) {
	this.texto = texto;
}
public String getImagen() {
	return imagen;
}
public void setImagen(String imagen) {
	this.imagen = imagen;
}
}
