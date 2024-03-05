
public class Circulo extends Figura {
	private double radio;
	
	public Circulo(double radio) {
		//super(0);
		this.radio = radio;
	}
	
	@Override
	public void calcularArea() {
		this.area = Math.PI * Math.pow(radio, 2);
		System.out.println("Area del circulo: " + this.area);
	}
	
	@Override
	public void calcularPerimetro() {
		this.perimetro = 2 * Math.PI * radio;
		System.out.println("Perimetro del circulo: " + this.perimetro);
	}
}
