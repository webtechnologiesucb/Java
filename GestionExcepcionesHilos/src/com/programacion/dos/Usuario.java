package com.programacion.dos;

import java.time.LocalDateTime;
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
	private LocalDateTime fechaReg;
	private String cuenta;
	private String contraseña;
	private boolean vigente;
}

public class Usuario{
	public static void main(String...args) {
		List<UsuarioModel> listado = new ArrayList<UsuarioModel>();
		listado.add(new UsuarioModel(1, "Miguel", "Pacheco", "Arteaga", 
				LocalDateTime.now(), "admin", "4dm1n123", true));
		listado.add(new UsuarioModel(2,"Leticia", "Garcia", "Martinez",
				LocalDateTime.now(), "lgarcia", "Leti2024", true));
		
		System.out.println("Uso del toString");
		for(UsuarioModel u : listado) {
			System.out.println(u.toString());
		}
		System.out.println("Uso del HashCode");
		for(UsuarioModel u : listado) {
			System.out.println(u.hashCode());
		}
		System.out.println("Uso de cuentas de usuario");
		for(UsuarioModel u : listado) {
			System.out.println(u.getCuenta());
		}
	}
}




