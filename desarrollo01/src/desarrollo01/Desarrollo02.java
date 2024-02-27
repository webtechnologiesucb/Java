package desarrollo01;

import java.util.Scanner;

public class Desarrollo02 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean disponible = true;
		double saldo = 0.00;
		int tipoTransaccion = 0;
		double monto = 0.00;
		while(disponible) {
			System.out.println("Tipo de Transacción (1=Deposito, 2=Retiro, 0=Salir del Programa): ");
			tipoTransaccion = sc.nextInt();
			
			switch(tipoTransaccion) {
				case 1 -> {
					System.out.print("\nIngrese el monto a depositar: ");
					monto = sc.nextDouble();
					saldo = saldo + monto;
				}
				case 2 -> {
					System.out.print("\nIngrese monto a retirar: ");
					monto = sc.nextDouble();
					if(monto > saldo)
						System.out.println("Transaccion invalida. Fondos Suficientes");
					else
						saldo -= monto; // saldo = saldo - monto;
				}
				case 0 -> disponible = false;
				default -> System.out.println("Tipo de transaccion invalido");
			}			
		}
		System.out.println("Saldo final (Bs): " + saldo);
		sc.nextLine();
	}
}
