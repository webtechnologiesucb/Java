package evaluacion.ucb;

//clase CuentaBancaria tener encapsulación
class CuentaBancaria {
	// atributos
	private String titular;
	private double saldo;
	//constructor
	public CuentaBancaria(String titular, double saldo) {
		this.titular = titular;
		this.saldo = saldo;
	}
	// metodo para depositar el dinero
	public void depositar(double cantidad) {
		if (cantidad > 0) {
			saldo += cantidad;
			System.out.println("Depósito exitoso. Nuevo saldo: " + saldo);
		} else {
			System.out.println("Cantidad no válida.");
		}
	}
	//metodo para retirar dinero con validacion
	public void retirar(double cantidad) {
		if(cantidad > 0 && cantidad <= saldo) {
			saldo -= cantidad;
			System.out.println("Retiro exitoso, nuevo saldo: " + saldo);
		} else {
			System.out.println("Saldo insuficiente / Cantidad no válida");
		}
	}
	// propiedad para consultar saldo (solo lectura getXX) 
	public double getSaldo() {	
		return saldo; 
	}
}

public class Banco {
	public static void main(String... args) {
		CuentaBancaria cuenta = new CuentaBancaria("Tony Stark", 5000);
		cuenta.depositar(1500);
		cuenta.retirar(2000);
		System.out.println("Saldo Final: $" + cuenta.getSaldo());
	}
}
