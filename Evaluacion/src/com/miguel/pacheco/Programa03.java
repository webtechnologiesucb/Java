package com.miguel.pacheco;

import java.util.Scanner;

/** dada la clase Operador, 
 * desarrollar con polimorfismo la clase calculadora
 */

class Operador{
	protected double operador1;
	protected double operador2;
	protected String operacion;
	
	public Operador() {
		this.operacion = " ";
		this.operador1 = 0.0;
		this.operador2 = 0.0;
	}
	
	public Operador(double op1, double op2, String signo) {
		this.operacion = signo;
		this.operador1 = op1;
		this.operador2 = op2;
	}
	
	public double ejecutar() {
		double res = 0;
		switch(this.operacion) {
			case "+" -> res = this.operador1 + this.operador2;
			case "-" -> res = this.operador1 - this.operador2;
			case "*" -> res = this.operador1 * this.operador2;
			case "/" -> res = this.operador1 / this.operador2;
		}
		return res;
	}
}


class Calculadora extends Operador{
	private double[] botonN;
	private char[] botonO;
	private char[] botonR;
	private double resultado;
	
	public Calculadora() {
		botonN = new double[10];
		botonO = new char[4];
		botonR = new char[3];
	}
	
	public void cargarBotones(){
		for (int i = 0; i < botonN.length; i++) {
			botonN[i] = i;
		}
		botonO[0] = '+';
		botonO[1] = '-';
		botonO[2] = '*';
		botonO[3] = '/';
		botonR[0] = 'C';
		botonR[1] = '=';
		botonR[2] = '.';
	}
	
	public void cancelar() {
		this.operacion = " ";
		this.operador1 = 0.0;
		this.operador2 = 0.0;
	}
	
	public void operar() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Ingrese operador1: ");
		this.operador1 = sc.nextDouble();
		System.out.print("Ingrese operador2: ");
		this.operador2 = sc.nextDouble();
		System.out.print("Ingrese operador: ");
		sc.nextLine();
		this.operacion = sc.nextLine();
	}
	
	public double igualar() {
		this.resultado = super.ejecutar();
		return this.resultado;
	}
	
}


public class Programa03 {

	public static void main(String[] args) {
		Operador o = new Calculadora();
		((Calculadora) o).operar();
		System.out.println(((Calculadora) o).igualar());
		
	}
}
