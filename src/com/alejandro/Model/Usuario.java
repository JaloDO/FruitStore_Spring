package com.alejandro.Model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class Usuario {

	@NotEmpty(message="Campo obligatorio")
	@Size(min = 5, max = 14, message="Debe tener entre 6 y 14 caracteres")
	private String username;
	
	@NotEmpty(message="Campo obligatorio")
	@Size(min = 6, max = 14, message="Debe tener entre 6 y 14 caracteres")
	private String password;
	
	@NotEmpty(message="Campo obligatorio")
	@Email(message="La dirección de correo no es válida")
	private String email;
	
	@NotEmpty(message="Campo obligatorio")
	@Size(max = 40, message="Debe tener menos de 40 caracteres")
	private String name;
	
	//@NotEmpty(message="Campo obligatorio")
	@NotNull
	@Min(value=18, message="Debe ser mayor de edad")
	private int age;
	
	public Usuario() {}

	public Usuario(String username, String password, String email, String name, int age) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.age = age;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Usuario [username=" + username + ", password=" + password + ", email=" + email + ", name=" + name
				+ ", age=" + age + "]";
	}
	
	
	
}
