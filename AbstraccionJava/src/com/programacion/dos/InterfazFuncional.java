package com.programacion.dos;

//Interfaz funcional que define una operación matemática con dos operandos y un resultado.
@FunctionalInterface
interface OperacionMatematica {
	// Método abstracto para realizar la operación matemática.
	int calcular(int a, int b);
}

//Clase principal que contiene el método main para ejecutar el programa.
public class InterfazFuncional {

	public static void main(String[] args) {
		// Creación de instancias de la interfaz OperacionMatematica utilizando
		// expresiones lambda.
		// La expresión lambda se utiliza para implementar el método abstracto de la
		// interfaz.
		OperacionMatematica suma = (a, b) -> a + b;
		OperacionMatematica resta = (a, b) -> a - b;
		OperacionMatematica producto = (a, b) -> a * b;
		OperacionMatematica division = (a, b) -> a / b;

		// Ejemplo de uso de las operaciones de suma y resta.
		System.out.println("Suma: " + suma.calcular(5, 3)); // Imprime el resultado de la suma.
		System.out.println("Resta: " + resta.calcular(5, 3)); // Imprime el resultado de la resta.
		System.out.println("Producto: " + producto.calcular(5, 3)); // Imprime el resultado de multiplicar.
		System.out.println("Division: " + division.calcular(5, 3)); // Imprime el resultado de dividir.
	}
}
