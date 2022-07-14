package com.ejemplo.Cine.CineRR;

import static spark.Spark.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LandingUser {


	

	static Scanner t=new Scanner(System.in);
	static Coneccion_BD conn;
	public LandingUser() {
		
		
	}
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		conn = new Coneccion_BD();
		System.out.println("----MENU---");
		System.out.println("----1 Inicio Sesion---");
		System.out.println("----2 Registro---");
		int op= t.nextInt();
		switch(op) {
		
		case 1:
			IniciarSesion();
			break;
		default:
			RegistrarNuevoUsuario();
			break;
		}		
	}
	
	public static void IniciarSesion() throws SQLException {
		
		System.out.println("Ingrese el mail");
		String mail=t.next();
		System.out.println("Ingrese password");
		String password=t.next();
		IniSession login=new IniSession(mail,password);
		ResultSet r=login.ingresar();
		if(r.next()) {
			System.out.println("Bienvenido otra vez");
			if(r.getInt("Rol")==1) {
				System.out.println("Bienvenido: \t"+r.getString("Nombre").concat("\t").concat(r.getString("Apellido")));
						
				User u=new User(r.getInt("idUser"),r.getString("Nombre"),r.getString("Apellido"),
						r.getString("mail"),r.getString("Dni"),r.getString("password"),r.getInt("Rol"));
				
				Adminis admin= new Adminis(u);
				
			}else {
				System.out.println("Bienvenido otra vez"+r.getString("Nombre"));
				
				User u=new User(r.getInt("idUsuario"),r.getString("Nombre"),r.getString("Apellido"),
						r.getString("mail"),r.getString("Dni"),r.getString("pass"),r.getInt("Rol"));
				User_Options menUser=new User_Options(u);
			}
			
			
		}else {
			
			System.out.print("Usuario y/o contraseña incorrecto");
		}
		
	}
	public static void RegistrarNuevoUsuario() throws SQLException {
		System.out.println("Registrarse");
		
		System.out.println("Ingrese su nombre");
		String n=t.next();
		System.out.println("Ingrese su Apellido");
		String ap=t.next();
		System.out.println("Ingrese su mail");
		String c=t.next();
		System.out.println("Ingrese su Dni");
		String d=t.next();
		System.out.println("Ingrese su Contraseña");
		String pass=t.next();


		if(n.isEmpty()==false) {
		
			ArrayList<String> listaDeUsuario=new ArrayList<>();
			listaDeUsuario.add(n);
			listaDeUsuario.add(ap);
			listaDeUsuario.add(c);
			listaDeUsuario.add(d);
			listaDeUsuario.add(pass);
			listaDeUsuario.add("2");
			conn.NuevoUser(listaDeUsuario);
		}

	}
}