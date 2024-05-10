package com.programacion.principal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.programacion.databases.Conexion;

public class Main {

	public static void main(String[] args) {
		try {
			Connection con = Conexion.getInstance().getConnection(); // rescatar conexion
			String consulta = "SELECT * FROM actor WHERE actor_id>?";
			PreparedStatement pst = con.prepareStatement(consulta); // Statement seguro
			pst.setString(1, "0"); //parametro tipo texto
			ResultSet rs = pst.executeQuery(); 
			System.out.println("ID\t Nombre\t\tApellido\t Fecha"); // encabezado de tabla
			while (rs.next()) { // mientras existan registros disponibles
				// entero: getInt, cadena: getString, fecha: getDate
				System.out.println(rs.getInt(1) + "\t" + 
						rs.getString(2) + "\t" + rs.getString(3) + "\t\t" + rs.getDate(4));
			}
			rs.close();
			pst.close();
			con.close();
		} catch (Exception e) {
			System.out.println("Excepcion: " + e.getMessage());
		} 
	}
}
