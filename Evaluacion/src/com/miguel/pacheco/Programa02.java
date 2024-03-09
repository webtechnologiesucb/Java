package com.miguel.pacheco;

import java.util.Scanner;

/**
 * Dado un numero ingresado por teclado, determinar sus divisores,
 * si el numero es primo y si el numero es perfecto
 */

class Numero{
	private int valor;
	
	public Numero(int valor) {
		this.setValor(valor);
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public void obtenerDivisores() {
		for (int divisor = 1; divisor <= (this.valor / 2); divisor++) {
			if (this.valor % divisor == 0)
				System.out.println(divisor + " ");
		}
	}
	
	public boolean esPrimo() {
		boolean res = false;
		int contador = 0;
		for (int divisor = 1; divisor <= (this.valor / 2); divisor++) {
			if (this.valor % divisor == 0)
				contador++;
		}
		if(contador == 1)
			res = true;
		return res;
	}
	
	public boolean esPerfecto() {
		boolean res = false;
		int sumador = 0;
		for (int divisor = 1; divisor <= (this.valor / 2); divisor++) {
			if (this.valor % divisor == 0)
				sumador += divisor;
		}
		if(sumador == this.valor)
			res = true;
		return res;
	}
}

public class Programa02 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese un numero entero: ");
		int valor = sc.nextInt();
		Numero n = new Numero(valor);
		System.out.println("Divisores del numero " + n.getValor());
		n.obtenerDivisores();
		System.out.println("El numero " + n.getValor() + " es primo: " + n.esPrimo());
		System.out.println("El numero " + n.getValor() + " es perfecto: " + n.esPerfecto());
	}

}
