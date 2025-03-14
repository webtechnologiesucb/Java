import java.util.Scanner;

class Operaciones{
	private int num1;
	private int num2;
	
	public Operaciones(int n1, int n2) {
		this.num1 = n1;
		this.num2 = n2;
	}
	
	public void sumar() {
		String res = String.format("%d", num1+num2);
		System.out.println("La suma es: " + res);
	}
	
	public void restar() {
		String res = String.format("%d", num1-num2);
		System.out.println("La resta es: " + res);
	}
	
	public void multiplicar() {
		String res = String.format("%d", num1*num2);
		System.out.println("La multiplicación es: " + res);
	}
	
	public void dividir() {
		String res = String.format("%d", num1/num2);
		System.out.println("La división es: " + res);
	}
}

public class AritmeticaBasica {

	public static void main(String[] args) {
		if(args.length > 0) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Ingrese el numero 1: ");
			int n1 = sc.nextInt();
			System.out.println("Ingrese el numero 2: ");
			int n2 = sc.nextInt();
			Operaciones ope = new Operaciones(n1, n2);
			switch(args[0]){
				case "suma" -> ope.sumar();
				case "resta" -> ope.restar();
				case "producto" -> ope.multiplicar();
				case "division" -> ope.dividir();
				default -> System.out.println("No se acepta el parametro ingresado");
			}
		} else {
			System.out.println("No tiene ningun argumento");
		}
	}

}
