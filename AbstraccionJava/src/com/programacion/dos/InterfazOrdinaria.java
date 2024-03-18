package com.programacion.dos;

//Interfaz para Figuras 3D
interface Figura3D {
	double calcularVolumen();

	double calcularAreaSuperficial();
}

//Clase base para Figuras 3D
abstract class Figura3DBase implements Figura3D {
	protected double area;
	protected double volumen;
	
	public Figura3DBase() {
	}
	
	abstract void imprimir();
}

//Clase para Esferas que extiende Figura3DBase
class Esfera extends Figura3DBase {
	private double radio;
	
	public Esfera(double radio) {
		this.radio = radio;
	}

	@Override
	public double calcularVolumen() {
		this.volumen = (4.0 / 3.0) * Math.PI * Math.pow(radio, 3);
		return this.volumen;
	}

	@Override
	public double calcularAreaSuperficial() {
		this.area = 4 * Math.PI * Math.pow(radio, 2);
		return this.area;
	}
	
	@Override
	void imprimir() {
		System.out.println("Area Esfera: " + this.calcularAreaSuperficial() +
				"\nVolumen Esfera: " + this.calcularVolumen());
	}
}

//Clase para Cilindros que extiende Figura3DBase
class Cilindro extends Figura3DBase {
	private double radio;
	private double altura;
	
	public Cilindro(double radio, double altura) {
		this.radio = radio;
		this.altura = altura;
	}

	@Override
	public double calcularVolumen() {
		this.volumen =  Math.PI * Math.pow(radio, 2) * altura;
		return this.volumen;
	}

	@Override
	public double calcularAreaSuperficial() {
		this.area = (2 * Math.PI * radio * altura) + (2 * Math.PI * Math.pow(radio, 2));
		return this.area;
	}
	
	@Override
	void imprimir() {
		System.out.println("Area Cilindro: " + calcularAreaSuperficial() +
				"\nVolumen Cilindro: " + calcularVolumen());
	}
}

public class InterfazOrdinaria {
	public static void main(String[] args) {
        Esfera esfera = new Esfera(3.0);
        Cilindro cilindro = new Cilindro(2.0, 5.0);
        
        esfera.imprimir();
        cilindro.imprimir();
    }
}
