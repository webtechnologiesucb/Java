package com.programacion.dos;

public class Avion extends Vehiculo implements Vuelo{

	@Override
	public void iniciar() {
		System.out.println("Empezando el vuelo del avion");		
	}

	@Override
	public void aterrizar() {
		System.out.println("Aterrizando vuelo del avion en pista");	
	}
	
	@Override
	public void enMarcha() {
		//super.enMarcha();
		System.out.println("En vuelo a 10000 pies");
	}

}
