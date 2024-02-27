package desarrollo01;

import java.util.Scanner;

public class Desarrollo01 {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		int num1, num2, num3;
		System.out.println("Ingrese 3 numeros enteros: ");
		num1 = sc.nextInt();
		num2 = sc.nextInt();
		num3 = sc.nextInt();
		
		int suma = num1 + num2 + num3;
		double promedio = (num1 + num2 + num3) / 3;
		int producto = num1 * num2 * num3;
		int menor = 0;
		if(num1 <= num2) {
			if(num1 <= num3) {
				menor = num1;
			} else {
				menor = num3;
			}
		} else {
			if(num2 <= num3) {
				menor = num2;
			} else {
				menor = num3;
			}
		}
		sc.nextLine();
		System.out.println("Suma de todos los numeros: " + suma);
		System.out.println("Promedio de todos los numeros: " + promedio);
		System.out.println("Producto de todos los numeros: " + producto);
		System.out.println("El menor de todos los numeros: " + menor);
	}
}
