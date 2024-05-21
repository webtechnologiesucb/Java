package com.programacion.dos;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class UsuarioModel {
	
	private int id;
	private String nombres;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private Date fechaReg; // para manejar fecha y hora
	private String cuenta;
	private String contraseña;
	private boolean vigente;
}

public class Usuario{
	public static void main(String...args) {
		List<UsuarioModel> listado = new ArrayList<UsuarioModel>();
		listado.add(new UsuarioModel(1, "Miguel", "Pacheco", "Arteaga", 
				Date.now(), "admin", "4dm1n123", true));
		listado.add(new UsuarioModel(2,"Leticia", "Martinez", "Garcia",
				Date.now(), "lgarcia", "L3ti2024", true));
		listado.add(new UsuarioModel(3,"Daniela", "Toledo", "Abastoflor",
				Date.now(), "dtoledo", "D4ni2024", true));
		
		System.out.println("*******************************************");
		System.out.println("Uso del toString");
		for(UsuarioModel u : listado) {
			System.out.println(u.toString());
		}
		System.out.println("*******************************************");
		System.out.println("Uso del HashCode");
		for(UsuarioModel u : listado) {
			System.out.println(u.hashCode());
		}
		System.out.println("*******************************************");
		System.out.println("Uso de cuentas de usuario");
		for(UsuarioModel u : listado) {
			System.out.println(u.getCuenta());
		}
		System.out.println("*******************************************");
	}
}




