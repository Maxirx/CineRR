package com.ejemplo.Cine.CineRR;

import static spark.Spark.*;

public class User {





	public int idUser;
	public String Nombre;
	public String Apellido;
	public String mail;
	public String dni;
	public String password;
	public int Rol;

	
	public User() {
		
	}
	
	public User(int idUser, String nombre, String apellido, String mail, String dni, String pass, int Rol) {
		super();
		this.idUser = idUser;
		this.Nombre = nombre;
		this.Apellido = apellido;
		this.mail = mail;
		this.dni = dni;
		this.password = pass;
		this.Rol = Rol;

	}
	public int getidUser() {
		return idUser;
	}
	public void idUser(int idUser) {
		this.idUser = idUser;
	}
	public String getNombre() {
		return Nombre;
	}
	public void Nombre(String Nombre) {
		this.Nombre = Nombre;
	}
	public String getApellido() {
		return Apellido;
	}
	public void Apellido(String Apellido) {
		this.Apellido = Apellido;
	}
	public String getmail() {
		return mail;
	}
	public void mail(String mail) {
		this.mail = mail;
	}
	public String getDni() {
		return dni;
	}
	public void sDni(String dni) {
		this.dni = dni;
	}
	public String getPassword() {
		return password;
	}
	public void password(String password) {
		this.password = password;
	}
	public int getRol() {
		return Rol;
	}
	public void Rol(int Rol) {
		this.Rol = Rol;
	}


		public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
}
