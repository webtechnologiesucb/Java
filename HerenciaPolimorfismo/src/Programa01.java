import java.util.Scanner;

public class Programa01 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
		System.out.println("Ingrese el radio del circulo: ");
		double r = sc.nextDouble();
		Circulo c = new Circulo(r);
		c.calcularArea();
		c.calcularPerimetro();
		sc.nextLine();

	}

}
