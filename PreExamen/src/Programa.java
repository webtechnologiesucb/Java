import java.util.Scanner;

class Figura3D {
	private double volumen;

	public Figura3D() {
		this.volumen = 0.0;
	}

	public void obtenerVolumen() {
		System.out.println("Calculando volumen ...");
	}
}

class Cilindro extends Figura3D {
	private double radio;
	private double altura;

	public Cilindro() {
	}

	public Cilindro(double r, double h) {
		this.radio = r;
		this.altura = h;
	}

	@Override
	public void obtenerVolumen() {
		double v = Math.PI * Math.pow(this.radio, 2) * this.altura;
		System.out.println("El volumen del cilindro es: " + v);
	}
}

public class Programa { // CTRL + SHIFT + F (dar formato)
	// CTRL + ESPACIO crear metodos e invocar a objetos
	// refactor para renombrar clases, propiedades y metodos
	// refactor para crear metodos
	// diagrama de clases de UML 
	// String args -> aplicaciones 
	// JAR 
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		double r, h;
		System.out.println("Ingrese el radio: ");
		r = sc.nextDouble();
		System.out.println("Ingrese la altura: ");
		h = sc.nextDouble();
		Figura3D f3 = new Cilindro(r, h);
		f3.obtenerVolumen();
	}
}
