package desarrollo01;

import java.util.Scanner;

public class Desarrollo041 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double ventasBrutas, it, iva, utilidadNeta;
		System.out.println("Ingrese el monto total de ventas: ");
		ventasBrutas = sc.nextDouble();
		it = ventasBrutas * 0.03;
		iva = ventasBrutas * 0.13;
		utilidadNeta = ventasBrutas - it - iva;
		System.out.println("Impuesto IVA: " + iva);
		System.out.println("Impuesto IT: " + it);
		System.out.println("Utilidad Neta: " + utilidadNeta);
		sc.nextLine();
	}

}
