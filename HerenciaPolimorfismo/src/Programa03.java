import java.util.Scanner;

public class Programa03 {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese su nombre: ");
		String nom = sc.nextLine();
		
		Demo.saludo(nom);
	}
}
