package desarrollo02;

import java.util.Scanner;

class Impuesto{
	private String nombre;
	private double tasa;
	private double monto;
	
	public Impuesto(double monto) {
		this.monto = monto;
	}
	
	
	public void capturar() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nombre de impuesto: ");
		this.nombre = sc.nextLine();
		System.out.println("Porcentaje (%): ");
		this.tasa = sc.nextDouble()/100.0;
	}
	
	double calcularImpuesto() {
		return this.monto * this.tasa;
	}
	
	void imprimir() {
		System.out.println("Para un valor de : " + this.monto 
				+ " y una tasa de: " + (this.tasa * 100) + " el valor de " +
				this.nombre + " es: " + calcularImpuesto());
	}
}

public class Desarrollo04 {
	public static void main(String[] args) {
		double monto = 0;
		double utilidadNeta = 0;
		System.out.println("Ingrese el total de ventas brutas: ");
		Scanner sc = new Scanner(System.in);
		monto = sc.nextDouble();
		
		Impuesto iva = new Impuesto(monto);
		iva.capturar();
		Impuesto it = new Impuesto(monto);
		it.capturar();
		utilidadNeta = monto - iva.calcularImpuesto() 
				- it.calcularImpuesto();
		
		iva.imprimir();
		it.imprimir();
		System.out.println("Para un valor de : " + monto 
				+ " la utilidad neta es " +	utilidadNeta);
		
		Impuesto iue = new Impuesto(utilidadNeta);
		iue.capturar();
		System.out.println("Para un valor de : " + utilidadNeta 
				+ " el impuesto es: " +	iue.calcularImpuesto());
		
	}
}
