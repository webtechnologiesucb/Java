package desarrollo02;

import java.util.Scanner;

class Cuenta{
	private double saldo;
	private int tipoOperacion;
	private boolean disponible;
	Scanner sc = new Scanner(System.in);
	
	public Cuenta() {
		this.saldo = 0;
		this.tipoOperacion = 9;
		this.disponible = true;
	}
	
	public void depositar() {
		System.out.print("\nIngrese el monto a depositar: ");
		double monto = sc.nextDouble();
		if (monto>0.00)
			this.saldo += monto;
	}
	
	public void retirar() {
		System.out.print("\nIngrese monto a retirar: ");
		double monto = sc.nextDouble();
		if(monto>this.saldo)
			System.out.println("Transaccion invalida. Fondos insuficientes");
		else
			this.saldo -= monto;
	}
	
	public void verSaldo() {
		System.out.println("Saldo de cuenta: " + this.saldo);
	}
	
	public void cerrarOperaciones() {
		this.disponible = false;
	}
	
	public void cargarOperacion() {
		System.out.println("Tipo de Transacción (1=Deposito, 2=Retiro, 3=Amortizar Interes, 0=Salir del Programa): ");
		this.tipoOperacion = sc.nextInt();
	}
	
	public boolean verEstado() {
		return this.disponible;
	}
	
	public int verOperacion() {
		return this.tipoOperacion;
	}
	
	public void amortizarInteres() {
		saldo += (saldo * 0.05); 
	}
}

public class Desarrollo02 {
	public static void main(String[] args) {
		Cuenta cta = new Cuenta();
		while(cta.verEstado()) {
			cta.cargarOperacion();
			
			switch(cta.verOperacion()) {
				case 1 -> {
					cta.depositar();
				}
				case 2 -> {
					cta.retirar();
				}
				case 3 -> {
					cta.amortizarInteres();
				}
				case 0 -> cta.cerrarOperaciones();
				default -> System.out.println("Tipo de transaccion invalido");
			}			
		}
		cta.verSaldo();
	}
}
