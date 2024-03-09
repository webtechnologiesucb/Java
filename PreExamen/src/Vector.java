import java.util.Random;

public class Vector {
	private int[] arreglo;
	private int tamaño;
	private long suma;
	private long producto;

	public Vector() {
		this.arreglo = new int[1000];
		this.tamaño = 1000;
	}

	public Vector(int tamaño) {
		this.arreglo = new int[tamaño];
		this.tamaño = tamaño;
	}

	public void generar() {
		Random r = new Random();
		for (int i = 0; i < tamaño; i++)
			arreglo[i] = r.nextInt(100);
	}

	public void sumar() {
		this.suma = 0;
		for (int valor : this.arreglo)
			this.suma += valor;
		System.out.println("La suma total: " + this.suma);
	}

	public void multiplicar() {
		this.producto = 1;
		for (int valor : this.arreglo)
			this.producto *= valor;
		System.out.println("El producto total: " + this.producto);
	}

	public void imprimir() {
		for (int valor : this.arreglo)
			System.out.print(valor + " ");
		System.out.println();
	}

	public void imprimirpares() {
		for (int valor : this.arreglo)
			if (valor % 2 == 0)
				System.out.print(valor + " ");
		System.out.println();
	}

	public static void main(String[] args) {
		Vector v = new Vector(10);
		v.generar();
		v.imprimir();
		v.imprimirpares();
		v.sumar();
		v.multiplicar();
	}

}
