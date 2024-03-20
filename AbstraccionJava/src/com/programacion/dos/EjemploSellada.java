package com.programacion.dos;

import java.util.Scanner;

//Clase sellada
sealed class Figura permits Circulo, Cuadrado {
	private double area;
	private double perimetro;
	private String nombre;

	public Figura() {
		this.area = 0.0;
		this.perimetro = 0.0;
	}

	public Figura(double a, double p, String nom) {
		this.setArea(a);
		this.setPerimetro(p);
		this.setNombre(nom);
	}

	public void setValores(double a, double p, String nom) {
		this.setArea(a);
		this.setPerimetro(p);
		this.setNombre(nom);
	}

	public void getValores() {
		System.out.println("Area del " + this.nombre + ": " + this.area);
		System.out.println("Perimetro del " + this.nombre + ": " + this.perimetro);
	}

	private double getArea() {
		return area;
	}

	private void setArea(double area) {
		this.area = area;
	}

	private double getPerimetro() {
		return perimetro;
	}

	private void setPerimetro(double perimetro) {
		this.perimetro = perimetro;
	}

	public void obtenerAreaPerimetro() {
		this.setValores(calcularArea(), calcularPerimetro(),"Figura");
	}
	
	private double calcularArea() {
		return this.area;
	}

	private double calcularPerimetro() {
		return this.perimetro;
	}

	private String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}
}

//Subclases permitidas
final class Circulo extends Figura {
	private double radio;

	public Circulo(double a, double p) {
		super(a, p, "Circulo");
		double r;
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese el radio: ");
		r = sc.nextDouble();
		sc.close();
		this.radio = r;
	}

	public Circulo(double r) {
		double a = 0.0;
		double p = 0.0;
		this.setValores(a, p, "Circulo");
		this.radio = r;
	}

	public Circulo(double a, double p, double r) {
		this.setValores(a, p, "Circulo");
		this.radio = r;
	}

	public void obtenerAreaPerimetro() {
		this.setValores(calcularArea(), calcularPerimetro(), "Circulo");
	}

	private double calcularArea() {
		return 2 * Math.PI * Math.pow(this.radio, 2);
	}

	private double calcularPerimetro() {
		return 2 * Math.PI * this.radio;
	}

}

final class Cuadrado extends Figura {
	private double lado;

	public Cuadrado(double a, double p) {
		super(a, p, "Cuadrado");
		double l;
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese el radio: ");
		l = sc.nextDouble();
		sc.close();
		this.lado = l;
	}

	public Cuadrado(double l) {
		double a = 0.0;
		double p = 0.0;
		this.setValores(a, p, "Cuadrado");
		this.lado = l;
	}

	public Cuadrado(double a, double p, double l) {
		this.setValores(a, p, "Cuadrado");
		this.lado = l;
	}

	public void obtenerAreaPerimetro() {
		this.setValores(calcularArea(), calcularPerimetro(), "Cuadrado");
	}

	private double calcularArea() {
		return Math.pow(this.lado, 2);
	}

	private double calcularPerimetro() {
		return 4 * this.lado;
	}
}

public class EjemploSellada {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Figura fci = new Circulo(0,0,2);
		fci.obtenerAreaPerimetro();
		fci.getValores();
		Figura fcu = new Cuadrado(0,0,2);
		fcu.obtenerAreaPerimetro();
		fcu.getValores();
	}
}
