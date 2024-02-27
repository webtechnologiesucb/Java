package desarrollo02;

import java.util.Scanner;

class Numero {    
	int num1;
	int num2;
	int num3;
	
	public Numero(int num1, int num2, int num3) {
		this.num1 = num1;
		this.num2 = num2;
		this.num3 = num3;
	}
	
    public int sumarNumeros() {
        return this.num1 + this.num2 + this.num3;
    }

    public double calcularPromedio() {
        return (double) (this.num1 + this.num2 + this.num3) / 3;
    }

    public int multiplicarNumeros() {
        return this.num1 * this.num2 * this.num3;
    }

    public int encontrarMenor() {
        int menor;
        if (this.num1 <= this.num2) {
            if (this.num1 <= this.num3) {
                menor = this.num1;
            } else {
                menor = this.num3;
            }
        } else {
            if (this.num2 <= this.num3) {
                menor = this.num2;
            } else {
                menor = this.num3;
            }
        }
        return menor;
    }
    
    public int encontrarMayor() {
        int menor;
        if (this.num1 >= this.num2) {
            if (this.num1 >= this.num3) {
                menor = this.num1;
            } else {
                menor = this.num3;
            }
        } else {
            if (this.num2 >= this.num3) {
                menor = this.num2;
            } else {
                menor = this.num3;
            }
        }
        return menor;
    }
}

public class Desarrollo01 {
	@SuppressWarnings("resource")
	public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese 3 numeros enteros: ");
        int num1 = sc.nextInt();
        int num2 = sc.nextInt();
        int num3 = sc.nextInt();
        Numero numero = new Numero(num1, num2, num3);

        System.out.println("Suma de todos los numeros: " + numero.sumarNumeros());
        System.out.println("Promedio de todos los numeros: " + numero.calcularPromedio());
        System.out.println("Producto de todos los numeros: " + numero.multiplicarNumeros());
        System.out.println("El menor de todos los numeros: " + numero.encontrarMenor());
        System.out.println("El mayor de todos los numeros: " + numero.encontrarMayor());
        sc = null;
    }
}
