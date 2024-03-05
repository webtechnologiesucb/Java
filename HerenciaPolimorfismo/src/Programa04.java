import java.util.Scanner;

public class Programa04 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
		System.out.println("Ingrese la base: ");
		double b = sc.nextDouble();
		System.out.println("Ingrese la altura: ");
		double h = sc.nextDouble();
		// Crea objeto figura y asigna el rectangulo (polimorfismo)
		Figura f = new Triangulo(b,h);
		// Llama al metodo calcularArea() de rectangulo a traves de la ref. Figura
		f.calcularArea();
		f.calcularPerimetro();
		sc.nextLine();
	}

}
