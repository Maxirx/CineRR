package com.ejemplo.Cine.CineRR;

import static spark.Spark.*;

import java.sql.*;
import java.util.ArrayList;

public class Coneccion_BD {


public Connection conn;
public Statement stmt;

	public  Coneccion_BD() {
		// nombre driver
	final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://localhost:3306/cine";
	// credenciales

	final String USER="root";
	final String PASS= "MYSQL123";
		

try {
	// registro
	Class.forName(JDBC_DRIVER);
	// nueva conexion
	System.out.println("conectando a BD");
	conn = DriverManager.getConnection(DB_URL, USER, PASS);
	

	
	
}catch(SQLException e){
	e.printStackTrace();
}catch(Exception e) {
	e.printStackTrace();
}

}
	public ResultSet RetornarConsulta(String query) throws SQLException {
		System.out.println("creando state");
		stmt= conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String sql;
		sql=query;
		
		ResultSet rs =stmt.executeQuery(sql);
		 return rs;
	}
	public void Consultas(String sql) throws SQLException {
		stmt.executeUpdate(sql);
	}
	
	public void NuevoElemento(String tabla, ArrayList<String> elementos) throws SQLException {
		
		System.out.println("Nuevo Statement");
		
		String sql;
		sql="insert into "+ tabla + "\r\n"
			+"values \r\n";
		for( int i=0;i<elementos.size()-1;i++) {
			
			sql.concat("("+"'"+elementos.get(i)+"'"+", \r\n");
		}
		sql.concat("'"+elementos.get(elementos.size())+"'"+")\r\n");
		sql.concat(";");
		stmt.executeUpdate(sql);
		System.out.print("nuevo elemento añadido");
		
	}
	
	public void NuevoUser(ArrayList<String> elementos) throws SQLException {
		System.out.println("Registrado");
		stmt= conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String sql;
		
	


		sql="insert into Usuarios (Nombre,Apellido,mail,Dni,password,Rol) "+
		" values (?,?,?,?,?,?)";
		
		 PreparedStatement preparedStmt = conn.prepareStatement(sql);
	    
	      preparedStmt.setString (1,elementos.get(0) );
	      preparedStmt.setString (2,elementos.get(1) );
	      preparedStmt.setString (3,elementos.get(2) );
	      preparedStmt.setString (4,elementos.get(3) );
	      preparedStmt.setString (5,elementos.get(4) );
	      preparedStmt.setInt (6,Integer.parseInt(elementos.get(5) ));
	    //  preparedStmt.setInt (7,Integer.parseInt(elementos.get(6)) );
	      preparedStmt.execute(); 
	      System.out.println("Registrado");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
