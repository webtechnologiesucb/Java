import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {
    	//Division con excepciones
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Ingresa el primer número: ");
            int num1 = Integer.parseInt(scanner.nextLine());

            System.out.print("Ingresa el segundo número: ");
            int num2 = Integer.parseInt(scanner.nextLine());

            int resultado = num1 / num2;
            System.out.println("El resultado de la división es: " + resultado);

        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresaste un valor que no es un número entero.");
        } catch (ArithmeticException e) {
            System.out.println("Error: No se puede dividir entre cero.");
        } finally {
            scanner.close();
        }
    }
}
