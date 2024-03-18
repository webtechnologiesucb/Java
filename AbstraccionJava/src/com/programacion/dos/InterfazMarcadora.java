package com.programacion.dos;

import java.io.Serializable; // Interfaz marcadora
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

// Clase que implementa la interfaz marcadora
class Persona implements Serializable {
	private String nombre;
	private int edad;

	// Constructor, getters y setters
	public Persona(String nombre, int edad) {
		this.nombre = nombre;
		this.edad = edad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String value) {
		this.nombre = value;
	}

	public int getEdad() {
		return this.edad;
	}

	public void setEdad(int value) {
		this.edad = value;
	}
}

public class InterfazMarcadora {
	public static void main(String[] args) {
		FileOutputStream fos = null;
		ObjectOutputStream salida = null;
		Persona p;
		try {
			System.out.println("Iniciando aplicacion");
			// Se crea el fichero binario
			fos = new FileOutputStream("personas.dat");
			salida = new ObjectOutputStream(fos);
			// Se crea el primer objeto Persona
			p = new Persona("Lucas González", 30);
			// Se escribe el objeto en el fichero
			salida.writeObject(p);
			// Se crea el segundo objeto Persona
			p = new Persona("Anacleto Jiménez", 28);
			// Se escribe el objeto en el fichero
			salida.writeObject(p);
			System.out.println("Cerrando aplicacion");
		} catch (FileNotFoundException e) {
			System.out.println("1. " + e.getMessage());
		} catch (IOException e) {
			System.out.println("2. " + e.getMessage());
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
				if (salida != null) {
					salida.close();
				}
			} catch (IOException e) {
				System.out.println("3. " + e.getMessage());
			}
		}
	}
}
