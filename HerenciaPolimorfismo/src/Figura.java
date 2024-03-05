
public class Figura {
	protected double area;
	protected double perimetro;
	
	public Figura() {}
	
	public Figura(double area) {
		this.area = area;
	}
	
	public void calcularArea() {
		System.out.println("Calculando el area ...");
	}
	
	public void calcularPerimetro() {
		System.out.println("Calculando el perímetro ...");
	}
}
