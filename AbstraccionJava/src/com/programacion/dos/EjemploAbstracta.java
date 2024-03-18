package com.programacion.dos;

abstract class Animal {
	String nombre;

	public Animal(String nombre) {
		this.nombre = nombre;
	}

	abstract void hacerSonido();
}

class Perro extends Animal {
	public Perro(String nombre) {
		super(nombre);
	}

	@Override
	void hacerSonido() {
		System.out.println(nombre + " hace guau guau");
	}
}

public class EjemploAbstracta {

	public static void main(String[] args) {
		Perro p = new Perro("Bobby");
		p.hacerSonido();
	}

}
