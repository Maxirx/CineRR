package com.ejemplo.Cine.CineRR;


import static spark.Spark.*;
import java.sql.*;
import java.util.Scanner;

public class User_Options {
	
	Scanner t=new Scanner(System.in);
	Coneccion_BD conn=new Coneccion_BD();
	public String[][] matrizResultado;
	
	public  User u;
	public User_Options(User u) throws SQLException {

		
		this.u=u;
		System.out.println("Menu");
		System.out.println("1 Reservar");
		System.out.println("2 consultar revervas.");// select where idUsario

		
		int op=t.nextInt();
		
		switch(op) {
		case 1:
			this.crearReserva();
			
			break;
			
		case 2:
			this.ConsultarReserva();
			break;

		}
		
	}
	
	public void crearReserva() throws SQLException {
		
		System.out.println("Peliculas Disponibles");
		int id=0;
		int idSala=0;
		int idReserva=0;
		String sql="select idCartelera,Nombre,Hora from Carteleras inner join Peliculas \n"
				+ "using(idPelicula) inner join Salas using(idSala);";
		
		
		ResultSet r=conn.RetornarConsulta(sql);
		
		System.out.println("id|\t Pelicuala|\t Hora|\t Numero de sala|\t");
		
		while(r.next()) {
			
			System.out.print(r.getInt("idCartelera")+"\t");
			System.out.print(r.getString("Nombre")+"\t");
			System.out.print(r.getString("Hora")+"\t");
			
		}
		
		System.out.println("seleccione el id de la Cartelera elegida");
		
		id=t.nextInt();
		

		String add="insert into Reservas values(null,"+u.getidUser()+","+1+","+1+","+id+");";
		conn.RetornarConsulta(add);
		
		System.out.println("Cantidad de entradas");
		int cant=t.nextInt();

		
		r=conn.RetornarConsulta("select Max(idReserva) as max from Reserva where idUsuario="+u.getidUser()+";");
		idReserva= r.getInt("max");
		
		
		
		
		r=conn.RetornarConsulta("select idSala from Carteleras where idCartelera="+id+";");
		if(r.next()) {
			idSala=r.getInt("idSala");
		}
		
		for(int i=1;i<=cant;i++) {
		r=conn.RetornarConsulta("select idButaca,Nombre from Butacas where idSala="+idSala+" and idEstado_Butaca=1;");
		
		System.out.println(" idButaca|\t Nombre|\t");
		while(r.next()) {
			System.out.print(r.getInt("idButaca")+"\t");
			System.out.print(r.getString("Nombre"));
		}
		System.out.println("id Butaca");
		int idButaca=t.nextInt();
		

		conn.RetornarConsulta("UPDATE `CineN6`.`Butacas` SET `idEstado_Butaca` = '2' WHERE (`idButaca` = "+idButaca+");");
		
		
		conn.RetornarConsulta("insert into Reserva_Butacas values("+idButaca+","+idReserva+")");
		
		
		}
		
		
	}
	public void ConsultarReserva() throws SQLException{
		System.out.println("Consultar Reservas");
		String sql="select Nombre,Hora from Carteleras inner join Peliculas";
		ResultSet r=conn.RetornarConsulta(sql);
		
		System.out.println(r);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}