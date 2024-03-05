
public class Triangulo extends Figura {
	private double base;
	private double altura;
	
	public Triangulo(double base, double altura) {
		this.base = base;
		this.altura = altura;
	}
	
	@Override
	public void calcularArea() {
		this.area = this.base * this.altura / 2;
		System.out.println("Area del triángulo: " + this.area);
	}
	
	@Override
	public void calcularPerimetro() {
		double baseMitad = this.base / 2;
		double lado = Math.sqrt(Math.pow(baseMitad, 2) + Math.pow(this.altura, 2));
		this.perimetro = this.base + lado + lado;
		System.out.println("Perímetros del triángulo: " + this.perimetro);
	}
}
