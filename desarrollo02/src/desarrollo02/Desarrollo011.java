package desarrollo02;

import java.util.Scanner;

class NumeroExtendido {    
	private int[] numero;
	private int tamaño;
	
	public NumeroExtendido(int tamaño) {
		this.numero = new int[tamaño];
		this.tamaño = tamaño;
	}
	
	public void cargarDatos() {
		Scanner sc = new Scanner(System.in);
		for(int i=0; i<tamaño; i++) {
			System.out.print("Ingrese numero entero " + (i+1) + ": ");
			numero[i] = sc.nextInt();
		}
	}
	
    public int sumarNumeros() {
    	int suma = 0;
    	for(int valor : this.numero)
    		suma += valor; 
        return suma;
    }

    public double calcularPromedio() {
        return (double) this.sumarNumeros() / tamaño;
    }

    public int multiplicarNumeros() {
    	int producto = 1;
    	for(int valor : this.numero)
    		producto *= valor; 
        return producto;
    }

    public int encontrarMenor() {
        int menor = Integer.MAX_VALUE;
        for(int valor : this.numero) {
        	if(valor < menor)
        		menor = valor;
        }
        return menor;
    }
}


public class Desarrollo011 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int cantidad = 0;
        System.out.println("Ingrese la cantidad de numeros que usara: ");
        cantidad = sc.nextInt();
        NumeroExtendido numero = new NumeroExtendido(cantidad);
        numero.cargarDatos();
        System.out.println("Suma de todos los numeros: " + numero.sumarNumeros());
        System.out.println("Promedio de todos los numeros: " + numero.calcularPromedio());
        System.out.println("Producto de todos los numeros: " + numero.multiplicarNumeros());
        System.out.println("El menor de todos los numeros: " + numero.encontrarMenor());
        sc = null;
	}
}
