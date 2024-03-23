package com.programacion.dos;

//Interfaz que define los métodos para operaciones en una cuenta bancaria
interface CuentaBancaria {
	void depositar(double cantidad);
	void retirar(double cantidad);
	double consultarSaldo();
}

//Clase abstracta que implementa la interfaz CuentaBancaria
abstract class CuentaBase implements CuentaBancaria {
	protected double saldo;

	public CuentaBase(double saldoInicial) {
		this.saldo = saldoInicial;
	}

	@Override
	public void depositar(double cantidad) {
		saldo += cantidad;
		System.out.println("Depósito de " + cantidad + " realizado. Nuevo saldo: " + saldo);
	}

	@Override
	public void retirar(double cantidad) {
		if (saldo >= cantidad) {
			saldo -= cantidad;
			System.out.println("Retiro de " + cantidad + " realizado. Nuevo saldo: " + saldo);
		} else {
			System.out.println("Saldo insuficiente para realizar el retiro.");
		}
	}

	@Override
	public double consultarSaldo() {
		return saldo;
	}
}

//Clase para una cuenta corriente que extiende la clase CuentaBase
class CuentaCorriente extends CuentaBase {
	private double comision;

	public CuentaCorriente(double saldoInicial, double comision) {
		super(saldoInicial);
		this.comision = comision;
	}

	@Override
	public void retirar(double cantidad) {
		super.retirar(cantidad + comision);
	}
}

//Clase para una cuenta de ahorros que extiende la clase CuentaBase
class CuentaAhorro extends CuentaBase {
	private double tasaInteres;

	public CuentaAhorro(double saldoInicial, double tasaInteres) {
		super(saldoInicial);
		this.tasaInteres = tasaInteres;
	}

	public void calcularInteres() {
		double interes = saldo * tasaInteres;
		depositar(interes);
	}
}

public class Ejercicio02 {
	public static void main(String[] args) {
		// Ejemplo de uso
		CuentaCorriente cuentaCorriente = new CuentaCorriente(1000, 0.5);
		cuentaCorriente.depositar(500);
		cuentaCorriente.retirar(200);

		CuentaAhorro cuentaAhorro = new CuentaAhorro(2000, 0.02);
		cuentaAhorro.depositar(1000);
		cuentaAhorro.calcularInteres();

		System.out.println("Saldo de la cuenta corriente: " + cuentaCorriente.consultarSaldo());
		System.out.println("Saldo de la cuenta de ahorros: " + cuentaAhorro.consultarSaldo());
	}
}
