package desarrollo02;

import java.util.Scanner;

class Rectangulo {
	private double base[];
	private double altura[];
	private int tamaño;
	
	public Rectangulo(int tamaño) {
		this.tamaño = tamaño;
		base = new double[tamaño];
		altura = new double[tamaño];
	}
	public void procesar() {
		Scanner sc = new Scanner(System.in);
		for(int i=0; i<this.tamaño; i++) {
			System.out.print("Ingrese altura numero " + (i+1) + ": ");
			this.altura[i] = sc.nextDouble();
			System.out.print("Ingrese base numero " + (i+1) + ": ");
			this.base[i] = sc.nextDouble();
			System.out.println("Perimetro: " + this.perimetro(base[i], altura[i]));
			System.out.println("Area: " + this.area(base[i], altura[i]));
		}
		sc.nextLine();
	}
	
	public double perimetro(double b, double h) {
		return 2 * (b + h);
	}
	
	public double area(double b, double h) {
		return (b * h);
	}
}

public class Extra {
	public static void main(String[] args) {
		System.out.println("Programa de radios");
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese la cantidad de rectangulos a ingresar: ");
		int tamaño = sc.nextInt();
		Rectangulo recta = new Rectangulo(tamaño);
		recta.procesar();

	}

}
