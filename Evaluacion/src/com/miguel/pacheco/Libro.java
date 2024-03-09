package com.miguel.pacheco;

import java.util.Scanner;

/**
 * La clase Libro para tener un detalle de venta de 3 libros
 */
public class Libro {
	private String autor; 
	private String titulo;
	private double precio;
	private double descuento;
	
	public Libro() {
		this.autor = "";
		this.titulo = "";
		this.precio = 0.0;
		this.descuento = 0.0;
	}
	
	public Libro(String autor, String titulo, double precio, double descuento){
		this.autor = autor;
		this.titulo = titulo;
		this.precio = precio;
		this.descuento = descuento;
	}
	
	public void ingresar(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese el titulo del libro");
		this.titulo = sc.nextLine();
		System.out.println("Ingrese el autor del libro");
		this.autor = sc.nextLine();
		System.out.println("Ingrese el precio del libro");
		this.precio = sc.nextDouble();
		System.out.println("Ingrese el descuento(%) del libro");
		this.descuento = sc.nextDouble();
		this.descuento = this.descuento / 100.0;
		sc.nextLine(); // salto de linea
	}
	
	public double calcularPrecioVenta() {
		return (this.precio * (1 - this.descuento));
	}
	
	public void imprimir() {
		System.out.println("El libro " + this.titulo + " del autor " + this.autor +
				" tiene el precio de Bs. " + this.calcularPrecioVenta());
	}
	
	@Override
	public String toString() {
		return "El libro " + this.titulo + " del autor " + this.autor +
				" tiene el precio de Bs. " + this.calcularPrecioVenta();
	}
}
