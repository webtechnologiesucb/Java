package desarrollo02;

import java.util.Scanner;

class Circulo{
	private double radio[];
	private int tamaño;
	
	public Circulo(int tamaño) {
		this.tamaño = tamaño;
		radio = new double[tamaño];
	}
	
	public void procesarRadios() {
		Scanner sc = new Scanner(System.in);
		for(int i=0; i<this.tamaño; i++) {
			System.out.print("Ingrese el radio numero " + (i+1) + ": ");
			this.radio[i] = sc.nextDouble();
			System.out.println("Diametro: " + this.diametro(radio[i]));
			System.out.println("Area: " + this.circunferencia(radio[i]));
		}
		sc.nextLine();
	}
	
	public double diametro(double r) {
		return r * 2;
	}
	
	public double circunferencia(double r) {
		return this.diametro(r) * Math.PI;
	}
}
	
public class Desarrollo03 {
	public static void main(String[] args) {
		System.out.println("Programa de radios");
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese la cantidad de radios a ingresar: ");
		int tamaño = sc.nextInt();
		Circulo circulo = new Circulo(tamaño);
		circulo.procesarRadios();
	}
}
