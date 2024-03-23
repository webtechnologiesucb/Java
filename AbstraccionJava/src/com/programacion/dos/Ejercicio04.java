package com.programacion.dos;

//Interfaz que define los métodos para un empleado
interface Empleado {
	void registrarEntrada();

	void registrarSalida();

	double calcularSalario();

	double calcularBeneficiosAdicionales();
}

//Clase abstracta que proporciona una implementación básica de los métodos de la interfaz Empleado
abstract class EmpleadoBase implements Empleado {
	protected String nombre;
	protected double salarioBase;

	public EmpleadoBase(String nombre, double salarioBase) {
		this.nombre = nombre;
		this.salarioBase = salarioBase;
	}

	@Override
	public void registrarEntrada() {
		System.out.println("Registro de entrada para el empleado " + nombre);
	}

	@Override
	public void registrarSalida() {
		System.out.println("Registro de salida para el empleado " + nombre);
	}

	@Override
	public double calcularSalario() {
		return salarioBase;
	}

	@Override
	public double calcularBeneficiosAdicionales() {
		return 0; // No hay beneficios adicionales por defecto
	}
}

//Clase concreta que representa a un empleado a tiempo completo
class EmpleadoTiempoCompleto extends EmpleadoBase {
	private double bonoAnual;

	public EmpleadoTiempoCompleto(String nombre, double salarioBase, double bonoAnual) {
		super(nombre, salarioBase);
		this.bonoAnual = bonoAnual;
	}

	@Override
	public double calcularSalario() {
		return super.calcularSalario() + bonoAnual;
	}

	@Override
	public double calcularBeneficiosAdicionales() {
		return bonoAnual;
	}
}

//Clase concreta que representa a un empleado a tiempo parcial
class EmpleadoTiempoParcial extends EmpleadoBase {
	private int horasTrabajadas;
	private double tarifaHora;

	public EmpleadoTiempoParcial(String nombre, double salarioBase, int horasTrabajadas, double tarifaHora) {
		super(nombre, salarioBase);
		this.horasTrabajadas = horasTrabajadas;
		this.tarifaHora = tarifaHora;
	}

	@Override
	public double calcularSalario() {
		return salarioBase + (horasTrabajadas * tarifaHora);
	}
}

public class Ejercicio04 {
	public static void main(String[] args) {
		// Ejemplo de uso
		Empleado empleadoTiempoCompleto = new EmpleadoTiempoCompleto("Juan", 2000, 500);
		empleadoTiempoCompleto.registrarEntrada();
		empleadoTiempoCompleto.registrarSalida();
		System.out.println("Salario del empleado a tiempo completo: " + empleadoTiempoCompleto.calcularSalario());
		System.out.println("Beneficios adicionales del empleado a tiempo completo: "
				+ empleadoTiempoCompleto.calcularBeneficiosAdicionales());

		Empleado empleadoTiempoParcial = new EmpleadoTiempoParcial("María", 15, 20, 10);
		empleadoTiempoParcial.registrarEntrada();
		empleadoTiempoParcial.registrarSalida();
		System.out.println("Salario del empleado a tiempo parcial: " + empleadoTiempoParcial.calcularSalario());
	}
}
