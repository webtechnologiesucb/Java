package com.programacion.dos;

import java.util.Scanner;

public class DivisionEjemplo {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a, b, c;
		try {
			a = sc.nextInt();
			b = sc.nextInt();
			c = a / b;
			System.out.println("Division: " + c);
		} catch(ArithmeticException e){
			System.out.println("Error de division por cero");
		}finally {
			sc.close();
		}
	}
}
