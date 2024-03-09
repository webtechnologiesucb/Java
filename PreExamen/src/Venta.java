import java.util.Scanner;

public class Venta {
	private String codigo;
	private String producto;
	private double precio;
	private int stock;
	
	public Venta() {
	}
	
	public void cargar() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese codigo:");
		this.codigo = sc.nextLine();
		System.out.println("Ingrese nombre del producto:");
		this.producto = sc.nextLine();
		System.out.println("Ingrese precio unitario: ");
		this.precio = sc.nextDouble();
		System.out.println("Ingrese cantidad de stock: ");
		this.stock = sc.nextInt();
	}
	
	public boolean buscar(String cod) {
		return this.codigo == cod;
	}
	
	public double obtenerSubtotal(int cantidad) {
		return this.precio * cantidad;
	}
	
	public static void main(String... args) {
		Venta v1 = new Venta();
		v1.cargar();
		Venta v2 = new Venta();
		v2.cargar();
		Venta v3 = new Venta();
		v3.cargar();
		// efectuando compra de 5 productos
		System.out.println("Subtotal 1: " + v1.obtenerSubtotal(5));
		System.out.println("Subtotal 2: " + v2.obtenerSubtotal(5));
		System.out.println("Subtotal 3: " + v3.obtenerSubtotal(5));
		
		System.out.print("Total venta: " + 
				v1.obtenerSubtotal(5) + (v2.obtenerSubtotal(5)
				+ v3.obtenerSubtotal(5)));
	}
}
