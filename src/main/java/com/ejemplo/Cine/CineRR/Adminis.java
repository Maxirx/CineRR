package com.ejemplo.Cine.CineRR;

import static spark.Spark.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Adminis {

	Scanner t=new Scanner(System.in);
	Coneccion_BD conn=new Coneccion_BD();
	public String[][] matrizResultado;
	
	public Adminis(User u) throws SQLException {

		System.out.println("Menu Administrador");
		System.out.println("1 Ver listado Salas");
		System.out.println("2 Crear una sala con la película.");
		System.out.println("3 Modificar una sala");
		System.out.println("4 Eliminar una sala.");
		System.out.println("5 Ver reservas de todos los clientes.");
		System.out.println("6 Ver reservas de un cliente en particular.");
		System.out.println("7 Modificar descuentos.");
	
		
	

		
		int op=t.nextInt();
		
		switch(op) {
		case 1:
			
			verSalas();
			break;
			
		case 2:
			this.crearCartelera();
			break;
		case 3:



			
			
		}
		
	}
	public void verSalas() throws SQLException {
		
		String sql="select * from Salas inner join Tipo_Sala using(idTipo_Sala);";
		ResultSet r=conn.RetornarConsulta(sql);
		
		System.out.println("idSala| \t Numero|\t Capacidad|\t Tipo");
		
		while(r.next()) {
		
			System.out.println(r.getInt("idSala")+"\t"+r.getString("Numero")+"\t"+
			r.getString("Capacidad")+"\t"+r.getString("tipo"));
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	public void crearCartelera() throws SQLException {
		
		int idSala=0;
		int idP=0;
		String Horario=null;
		System.out.println("Esta por crear una cartelera");
		
		System.out.println("Seleccione la sala");
		
		ResultSet r=conn.RetornarConsulta("select idSala,Numero,Capacidad,Tipo from Salas inner join Tipo_Salausing(idTipo_Sala);");
		
		System.out.println("idSala|\t Numero|\t Capacidad|\t Tipo\t");
		
		while(r.next()) {
			System.out.print(r.getInt("idSala")+"\t");
			System.out.print(r.getString("Numero")+"\t");
			System.out.print(r.getString("Capacidad")+"\t");
			System.out.print(r.getString("Tipo")+"\t");
			System.out.print(r.getString("\n"));
		}
		System.out.println("Ingrese el id de la sala");
		idSala=t.nextInt();
		
		
		System.out.println("Seleccione la pelicula");
		r=conn.RetornarConsulta("select * from Peliculas;");
		System.out.println("idPelicula|\t Nombre|\t Duracion");
		
		while(r.next()) {

			System.out.print(r.getInt("idPelicula")+"\t");
			System.out.print(r.getString("Nombre")+"\t");
			System.out.print(r.getString("Duracion")+"\t");
			
		}
		System.out.println("selecciona el id de la pelicula");
		idP=t.nextInt();
		
		System.out.println("Ingrese el horario");
		Horario=t.next();
		
		String sql="insert into cartelera values (null,"+idP+","+"'"+Horario+"'"+","+idSala+");";
		conn.Consultas(sql);
		
		System.out.println("Cartelera creada con exito");
		
	}
	

}
