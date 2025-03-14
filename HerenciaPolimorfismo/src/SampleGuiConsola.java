
public class SampleGuiConsola {
    public static void main(String[] args) {
        if (args.length > 0) {
            String mode = args[0];

            if ("console".equalsIgnoreCase(mode)) {
                runConsoleVersion();
            } else if ("gui".equalsIgnoreCase(mode)) {
                runGuiVersion();
            } else {
                System.out.println("Modo no reconocido. Usa 'console' o 'gui'.");
            }
        } else {
            System.out.println("Por favor, proporciona un parámetro de modo: 'console' o 'gui'.");
        }
    }

    private static void runConsoleVersion() {
        // Lógica para la versión de consola
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Ingresa el primer número: ");
        int num1 = scanner.nextInt();
        System.out.print("Ingresa el segundo número: ");
        int num2 = scanner.nextInt();
        System.out.println("La suma es: " + (num1 + num2));
        scanner.close();
    }

    private static void runGuiVersion() {
        // Lógica para la versión gráfica
        javax.swing.JOptionPane.showMessageDialog(null, "Bienvenido a la versión gráfica");
        String num1 = javax.swing.JOptionPane.showInputDialog("Ingresa el primer número:");
        String num2 = javax.swing.JOptionPane.showInputDialog("Ingresa el segundo número:");
        int sum = Integer.parseInt(num1) + Integer.parseInt(num2);
        javax.swing.JOptionPane.showMessageDialog(null, "La suma es: " + sum);
    }
}
