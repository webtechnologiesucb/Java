package com.programacion.dos;

record Estudiante(String nombre, int edad) {
    // No se requieren campos adicionales o métodos
	// No es necesario definir getters, setters, equals(), hashCode() o toString(),
}

public class EjemploRecord {

	public static void main(String[] args) {
		Estudiante p = new Estudiante("Maria Lopez", 23);
		Estudiante p1 = new Estudiante("Marco Perez", 24);
		System.out.println(p.toString());
		System.out.println("Hash(p)=" + p.hashCode());
		System.out.println(p1.toString());
		System.out.println("Hash(p1)=" + p1.hashCode());
		System.out.println("p1 = p :" + p.equals(p1));
	}

}
