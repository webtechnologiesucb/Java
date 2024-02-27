package desarrollo01;

import java.util.Scanner;

public class Desarrollo042 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double ventasBrutas;
		System.out.println("Ingrese el monto total de ventas: ");
		ventasBrutas = sc.nextDouble();
		System.out.println("Impuesto IVA: " + obtenerIVA(ventasBrutas));
		System.out.println("Impuesto IT: " + obtenerIT(ventasBrutas));
		System.out.println("Utilidad Neta: " + obtenerUtilidad(ventasBrutas));
		sc.nextLine();
	}

	private static double obtenerUtilidad(double ventasBrutas) {
		return ventasBrutas - obtenerIT(ventasBrutas) - obtenerIVA(ventasBrutas);
	}

	private static double obtenerIVA(double ventasBrutas) {
		return ventasBrutas * 0.13;
	}

	private static double obtenerIT(double ventasBrutas) {
		return ventasBrutas * 0.03;
	}
}
