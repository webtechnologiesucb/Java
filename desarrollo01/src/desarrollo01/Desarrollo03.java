package desarrollo01;

import java.util.Scanner;

public class Desarrollo03 {
	public static void main(String ... args) {
		Scanner sc = new Scanner(System.in);
		double[] radio = new double[5];
		for(int i=0; i<5; i++) {
			System.out.println("Ingrese radio numero " + i + ": ");
		    radio[i] =	sc.nextDouble();
		    System.out.println("Diametro del circulo: " + diametro(radio[i]));
		    System.out.println("Circunferencia: " + circunferencia(radio[i]));
		}
		sc.nextLine();
		System.out.println();
	}
	
	public static double diametro(double r) {
		return r * 2;
	}
	
	public static double circunferencia(double r) {
		return diametro(r) * Math.PI;
	}
}
