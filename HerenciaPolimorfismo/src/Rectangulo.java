
public class Rectangulo extends Figura {
	private double base;
	private double altura;
	
	public Rectangulo(double base, double altura) {
		this.base = base;
		this.altura = altura;
	}
	
	@Override
	public void calcularArea() {
		this.area = this.base * this.altura;
		System.out.println("Area del rectángulo: " + this.area);
	}
}
