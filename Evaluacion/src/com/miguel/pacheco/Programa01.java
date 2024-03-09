package com.miguel.pacheco;

public class Programa01 {
	public static void main(String[] args) {
		Libro l1 = new Libro();
		Libro l2 = new Libro();
		Libro l3 = new Libro();
		System.out.println("Ingrese los datos del libro 1");
		l1.ingresar();
		System.out.println("Ingrese los datos del libro 2");
		l2.ingresar();
		System.out.println("Ingrese los datos del libro 3");
		l3.ingresar();
		
		System.out.println("Detalle de las ventas de los 3 libros");
		l1.imprimir();
		l2.imprimir();
		l3.imprimir();
		
		System.out.println("Total de ventas: Bs. " + (l1.calcularPrecioVenta() +
				l2.calcularPrecioVenta() + l3.calcularPrecioVenta()) );
		
		System.out.println(l1.toString()); 
	}
}
